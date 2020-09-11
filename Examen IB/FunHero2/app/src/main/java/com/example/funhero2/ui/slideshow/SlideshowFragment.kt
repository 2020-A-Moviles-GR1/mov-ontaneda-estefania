package com.example.funhero2.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.ComicHttp
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_slideshow.*
import com.github.kittinunf.result.Result

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    val urlPrincipal = "http://192.168.1.4:1337"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)


        //var listaComicMemoria : ArrayList<ComicMod> = ServicioBDDMemoria.listaComic
        var listaComicMemoria = obtenerComics()
        var lista_Comics:ListView = root.findViewById(R.id.lv_listaComic)

        val adaptador = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listaComicMemoria)
        lista_Comics.setAdapter(adaptador)

        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
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
