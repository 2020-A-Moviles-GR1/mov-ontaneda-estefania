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
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

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

        var listaComicMemoria = ServicioBDDMemoria.listaComic
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
                /*listaComicMemoria.removeAt(position)
                listaSuperheroe.removeAt(posSuper)
                adaptador.notifyDataSetChanged()*/
                val alertDialog = AlertDialog.Builder(requireActivity()).create()
                alertDialog.setTitle("Vas a borrar")
                alertDialog.setMessage("Si eliminas el CÓMIC --> MATAS SUPERHEROES :(")

                alertDialog.setButton(
                    AlertDialog.BUTTON_POSITIVE, "Yes"
                ) { dialog, which ->
                    listaComicMemoria.removeAt(position)
                    listaSuperheroe.removeAt(posSuper)
                    adaptador.notifyDataSetChanged()

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
}
