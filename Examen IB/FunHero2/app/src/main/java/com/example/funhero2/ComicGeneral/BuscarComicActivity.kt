package com.example.funhero2.ComicGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria

class BuscarComicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_comic)

        var lista_Comic: ListView = findViewById(R.id.lv_buscarComic)
        var listaComicMemoria : ArrayList<ComicMod> =
            ServicioBDDMemoria.listaComic
        var nomComic : EditText = findViewById(R.id.et_nombreComiBuscar)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaComicMemoria)

        var mSearchTw = object : TextWatcher {

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                adaptador.getFilter().filter(s)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }

        lista_Comic.setAdapter(adaptador)
        nomComic.addTextChangedListener(mSearchTw)
    }

}
