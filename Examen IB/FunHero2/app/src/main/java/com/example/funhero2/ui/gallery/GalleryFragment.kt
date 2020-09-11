package com.example.funhero2.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.ComicHttp
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_gallery.*
import com.github.kittinunf.result.Result

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    val urlPrincipal = "http://192.168.1.4:1337"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)

        var lista_Comics: ListView = root.findViewById(R.id.lv_comicsEliminar)

        /*var listaComicMemoria = ServicioBDDMemoria.listaComic
        var listaSuperheroe = ServicioBDDMemoria.listaSuperheroe*/
        var listaComicMemoria = obtenerComics()
        var listaSuperheroe = ServicioBDDMemoria.listaSuperheroe
        val adaptador = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listaComicMemoria)
        lista_Comics.setAdapter(adaptador)

        lista_Comics.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            Log.i("List-view", "Posicion: $position")

            var comicSeleccionado = listaComicMemoria[position]
            var superheroeEliminado = comicSeleccionado.nombreComic
            var posSuper : Int =0

            listaSuperheroe.forEach {
                if(superheroeEliminado == it.comicName){
                    posSuper= listaSuperheroe.indexOf(it)
                }
            }

            btn_eliminarComic.setOnClickListener {
                val alertDialog = AlertDialog.Builder(requireActivity()).create()
                alertDialog.setTitle("Vas a borrar")
                alertDialog.setMessage("Si eliminas el CÓMIC --> MATAS SUPERHEROES :(")

                alertDialog.setButton(
                    AlertDialog.BUTTON_POSITIVE, "Yes"
                ) { dialog, which ->
                    listaComicMemoria.removeAt(position)
                    listaSuperheroe.removeAt(posSuper)
                    adaptador.notifyDataSetChanged()

                    var nombre = listaComicMemoria.get(position).nombreComic
                    var idComic = obteneridComic(nombre)
                    delete_comic(idComic)


                    Snackbar.make(view, "CÓMIC ELIMINADO Y SUPERHEROES MUERTOS :(", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show() }

                alertDialog.setButton(
                    AlertDialog.BUTTON_NEGATIVE, "No"
                ) { dialog, which -> dialog.dismiss() }
                alertDialog.show()

                val btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)

                val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
                layoutParams.weight = 10f
                btnPositive.layoutParams = layoutParams
                btnNegative.layoutParams = layoutParams
            }
        }

        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    fun obteneridComic(nombre: String):Int{
        val url = urlPrincipal + "/comic"
        var listaComic = arrayListOf<ComicMod>()
        var idComic = 0
        var peticion = url.httpGet().responseString { request, response, result ->
            when(result){
                is Result.Success ->{
                    val data = result.get()
                    Log.i("KLAXON", "DATA ${data}")
                    val comics = Klaxon().parseArray<ComicHttp>(data)
                    if(comics != null){
                        comics.forEach {
                            Log.i("KLAXON", "ID: ${it.id}")
                            Log.i("ID", "ID: ${nombre}")
                            if(nombre == it.nombreComic){
                                idComic= it.id
                                Log.i("ID", "ID: ${idComic}")
                            }
                        }
                    }
                }
                is Result.Failure ->{
                    val error = result.getException()
                    Log.i("Error", "ERROR: ${error}")
                }
            }
        }
        peticion.join()
        return idComic
    }

    fun delete_comic(
        posicion: Int

    ) {
        val url = urlPrincipal + "/comic" + "/" + posicion
        Log.i("url_put", url)
        url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("Error", "El error al eliminar comic: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al eliminar: ${usuarioString}")
                }
            }
        }
    }

    fun obtenerComics(): ArrayList<ComicMod>{
        val url = urlPrincipal + "/comic"
        var listaComics = arrayListOf<ComicMod>()
        var peticion = url.httpGet().responseString { request, response, result ->
            when (result){
                is Result.Success ->{
                    val data = result.get()
                    Log.i("KLAXON-SUCESS", "COMICS EN SAILS:${data}")
                    val comics = Klaxon().parseArray<ComicHttp>(data)
                    if(comics != null){
                        comics.forEach{
                            Log.i("KLAXON", "NOMBRE COMIC: ${it.nombreComic}")
                            listaComics.add(ComicMod(it.nombreComic.toString(),it.vigencia.toString(),it.paginas.toString(),it.precio.toString()))
                        }
                    }
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("KLAXON", "ERROR FAILURE:${ex.message}")
                }
            }
        }
        peticion.join()
        return listaComics
    }
}
