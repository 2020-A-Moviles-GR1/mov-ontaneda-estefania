package com.example.funhero2.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)

        var lista_Comics:ListView = root.findViewById(R.id.lv_listaComic)
        var listaComicMemoria : ArrayList<ComicMod> = ServicioBDDMemoria.listaComic

        val adaptador = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listaComicMemoria)
        lista_Comics.setAdapter(adaptador)

        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it

        })
        return root
    }

}
