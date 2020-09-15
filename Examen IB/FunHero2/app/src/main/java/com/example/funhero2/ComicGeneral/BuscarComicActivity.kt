package com.example.funhero2.ComicGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.ComicHttp
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class BuscarComicActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_comic)

        var lista_Comic: ListView = findViewById(R.id.lv_buscarComic)
        var listaComicMemoria : ArrayList<ComicMod> = obtenerComics()
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
