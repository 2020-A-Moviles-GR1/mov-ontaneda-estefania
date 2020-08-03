package com.example.funhero2.ComicGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_actualizar_comic.*

class actualizarComic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_comic)

        et_precioComicAct.visibility = View.GONE
        sp_actVigencia.visibility = View.GONE

        var lista_Comics: ListView = findViewById(R.id.lv_comicsAct)
        var listaComicMemoria: ArrayList<ComicMod> =
            ServicioBDDMemoria.listaComic
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
                var pagComic: String = comicDeseado.pags.toString()
                //et_pagComicAct.setText(pagComic)
                var precioComic: String = comicDeseado.precio.toString()
                //tv_precioAnterior.setText(precioComic)
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
                            var precioNuevoComp = precioNuevo.toDouble()
                            if (precioNuevoComp != comicDeseado.precio) {
                                comicDeseado.precio = precioNuevoComp
                                Snackbar.make(it, "PRECIO ACTUALIZADO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_precioAnterior.setText("")
                                et_precioComicAct.setText("")
                                adaptador.notifyDataSetChanged()
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
                                comicDeseado.vigencia = true
                                Snackbar.make(it, "VIGENCIA ACTUALIZADA", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_vigenciaAnterior.setText("Vigente")
                                adaptador.notifyDataSetChanged()
                            } else {
                                comicDeseado.vigencia = false
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
                            var precioNuevoComp = precioNuevo.toDouble()
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
                                comicDeseado.vigencia = true
                                Snackbar.make(it, "VIGENCIA ACTUALIZADA", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                                et_vigenciaAnterior.setText("Vigente")
                                adaptador.notifyDataSetChanged()
                            } else {
                                comicDeseado.vigencia = false
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
}



