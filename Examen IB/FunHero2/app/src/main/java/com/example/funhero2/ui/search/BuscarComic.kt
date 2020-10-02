package com.example.funhero2.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria


class BuscarComic : Fragment() {

    private lateinit var viewModel: BuscarComicViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BuscarComicViewModel::class.java)
        val root = inflater.inflate(R.layout.buscar_comic_fragment, container, false)
        //var lista_Comics: ListView = root.findViewById(R.id.lv_buscarComic)
        //var listaComicMemoria : ArrayList<ComicMod> = ServicioBDDMemoria.listaComic

        //val adaptador = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listaComicMemoria)
        //lista_Comics.setAdapter(adaptador)
        var mSearchTw = object : TextWatcher {

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
          //      adaptador.getFilter().filter(s)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel

    }

}
