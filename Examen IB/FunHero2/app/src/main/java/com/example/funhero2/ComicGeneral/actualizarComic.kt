package com.example.funhero2.ComicGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.ComicHttp
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_actualizar_comic.*

class actualizarComic : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_comic)

        et_precioComicAct.visibility = View.GONE
        sp_actVigencia.visibility = View.GONE

        var lista_Comics: ListView = findViewById(R.id.lv_comicsAct)
        var listaComicMemoria = obtenerComics()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaComicMemoria)

        lista_Comics.setAdapter(adaptador)

        lista_Comics.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("List-view", "Posicion: $position")
                var comicDeseado = listaComicMemoria[position]
                var nomComic = comicDeseado.nombreComic
                //et_nomComicAct.setText(nomComic)
                var vigencia: String = comicDeseado.vigencia.toString()
                if (vigencia.equals("true")) {
                    et_vigenciaAnterior.setText("Vigente")
                } else {
                    et_vigenciaAnterior.setText("No vigente")
                }
                //et_vigComicAct.setText(vigencia)
                var pagComic: String = comicDeseado.paginas.toString()
                //et_pagComicAct.setText(pagComic)
                var precioComic: String = comicDeseado.precio.toString()

                et_precioAnterior.setText(precioComic)

                btn_actPrecio.setOnClickListener {
                    et_precioComicAct.visibility = View.VISIBLE
                }

                btn_actVigencia.setOnClickListener {
                    sp_actVigencia.visibility = View.VISIBLE
                }

                btn_actComic.setOnClickListener {
                    if (et_precioComicAct.visibility == View.VISIBLE && sp_actVigencia.visibility == View.GONE) {
                        var precioNuevo = et_precioComicAct.text.toString()
                        if (precioNuevo != "") {
                            var precioNuevoComp = precioNuevo.toString()
                            if (precioNuevoComp != comicDeseado.precio) {
                                comicDeseado.precio = precioNuevoComp
                                Snackbar.make(it, "PRECIO ACTUALIZADO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_precioAnterior.setText("")
                                et_precioComicAct.setText("")
                                adaptador.notifyDataSetChanged()
                                put_comic(position+1,precioNuevo)
                            } else {
                                Snackbar.make(it, "EL PRECIO ES EL MISMO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                            }
                        } else {
                            Snackbar.make(it, "INGRESE VALOR NUEVO", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show()
                        }

                    } else if (sp_actVigencia.visibility == View.VISIBLE && et_precioComicAct.visibility == View.GONE) {

                        var vigenciaNueva = sp_actVigencia.selectedItem.toString()
                        if (vigenciaNueva != comicDeseado.vigencia.toString()) {
                            if (vigenciaNueva.equals("Vigente")) {
                                comicDeseado.vigencia = "true"
                                Snackbar.make(it, "VIGENCIA ACTUALIZADA", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_vigenciaAnterior.setText("Vigente")
                                adaptador.notifyDataSetChanged()
                            } else {
                                comicDeseado.vigencia = "false"
                                Snackbar.make(it, "VIGENCIA ACTUALIZADA", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_vigenciaAnterior.setText("No vigente")
                                adaptador.notifyDataSetChanged()
                            }
                        } else {
                            Snackbar.make(it, "LA VIGENCIA ES LA MISMA", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show()
                        }
                    } else if (sp_actVigencia.visibility == View.VISIBLE && et_precioComicAct.visibility == View.VISIBLE) {
                        var precioNuevo = et_precioComicAct.text.toString()
                        if (precioNuevo != "") {
                            var precioNuevoComp = precioNuevo.toString()
                            if (precioNuevoComp != comicDeseado.precio) {
                                comicDeseado.precio = precioNuevoComp
                                Snackbar.make(it, "PRECIO ACTUALIZADO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                adaptador.notifyDataSetChanged()
                            } else {
                                Snackbar.make(it, "EL PRECIO ES EL MISMO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                            }
                        } else {
                            Snackbar.make(it, "INGRESE VALOR NUEVO", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show()
                        }

                        var vigenciaNueva = sp_actVigencia.selectedItem.toString()
                        if (vigenciaNueva != comicDeseado.vigencia.toString()) {
                            if (vigenciaNueva.equals("Vigente")) {
                                comicDeseado.vigencia = "true"
                                Snackbar.make(it, "VIGENCIA ACTUALIZADA", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_vigenciaAnterior.setText("Vigente")
                                adaptador.notifyDataSetChanged()
                            } else {
                                comicDeseado.vigencia = "false"
                                Snackbar.make(it, "VIGENCIA ACTUALIZADA", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_vigenciaAnterior.setText("No vigente")
                                adaptador.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
    }

    fun put_comic(
        posicion:Int,
        nuevo_precio: String
    ){
        val url = urlPrincipal + "/comic" +"/" + posicion
        val parametrosComic = listOf(
            "precio" to nuevo_precio
        )
        url.httpPut(parametrosComic).responseString { request, response, result ->
            when(result){
                is Result.Success -> {
                    val comicString = result.get()
                    Log.i("KLAXON", "Éxito al actualizar ${comicString}")
                }
                is Result.Failure ->{
                    val error = result.getException()
                    Log.i("Error", "ERROR AL ACTUALIZAR COMIC: ${error}")
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



