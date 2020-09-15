package com.example.funhero2.ComicGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_insertar_comic.*

import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.ComicHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class InsertarComic : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_comic)

        var listaComic = arrayListOf<ComicHttp>()
        btn_agregarComic.setOnClickListener {
            if(crearComic() == true){
                Snackbar.make(it, "COMIC INSERTADO", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }else{
                Snackbar.make(it, "ERROR AL CREAR COMIC", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    fun crearComic():Boolean{
        val url = urlPrincipal + "/Comic"
        var flag = true
        val listaDeParametros = obtenerParametros()
        url.httpPost(listaDeParametros).responseString {
                request, response, result ->
            when(result){
                is Result.Success ->{
                    val comicHttp = result.get()
                    Log.i("KLAXON", "COMIC CREADO: ${comicHttp}")
                    limpiarCajas()
                    flag = true
                }
                is Result.Failure ->{
                    val error = result.getException()
                    Log.i("KLAXON", "Error al crear COMIC: ${error.message}")
                    flag = false
                }
            }
        }
        return flag
    }

    fun limpiarCajas(){
        et_nombreComic.setText("")
       // et_vigenciaComic.setText("")
        et_pagsComic.setText("")
        et_precioComic.setText("")
    }

    fun obtenerValorSpinner():Boolean{
        val vigComic = spinner.selectedItem.toString()
        if(vigComic.equals("Vigente")){
            return true
        }else{
            return false
        }
        return true
    }

    fun obtenerParametros():List<Pair<String,String>>{
        val listaDeParametros = listOf(
            "nombreComic" to et_nombreComic.text.toString(),
            "vigencia" to obtenerValorSpinner().toString(),
            "paginas" to et_pagsComic.text.toString(),
            "precio" to et_precioComic.text.toString()
        )
        return listaDeParametros
    }
    }

