package com.example.funhero2.SuperheroeGeneral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.beust.klaxon.Klaxon
import com.example.funhero2.Maps.Act_insertarLatitudLongitud
import com.example.funhero2.Modelo.ComicHttp
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_insertar_supeerheroe.*
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_insertar_comic.*

class insertarSupeerheroe : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"

    var nameSuperheroe = ""
    var single: String = "true"
    var streghtForceLevel: String = "0.00"
    var age: Int = 0
    var comicName: String =""
    var latitudSup:Double = 0.00
    var longitudSup:Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_supeerheroe)

        var listaComics: Spinner =findViewById(R.id.sp_comicsSuper)
        var listaComicsMemoria = obtenerComic()

        et_nombreSupInsertar.setText(ServicioBDDMemoria.nameSuperheroe)

        if(ServicioBDDMemoria.single.equals("true")){
            rb_soltero.isChecked == true
        }else{
            rb_casado.isChecked == true
        }
        et_fuerzaInsertar.setText(ServicioBDDMemoria.streghtForceLevel)
        et_edadSupInsertar.setText(ServicioBDDMemoria.age)

        val adaptadordatos= ArrayAdapter(
            this,android.R.layout.simple_spinner_item,
            listaComicsMemoria
        )
        listaComics.setAdapter(adaptadordatos)

        btn_AgregarSuperheroe.setOnClickListener {

            if(crearSuperheroe() == true){
            Snackbar.make(it, "SUPERHEROE INSERTADO", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            }else{
                Snackbar.make(it, "Problema al insertar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        btn_ingresoLatLgn.setOnClickListener {
            irMapa()
        }

    }

    fun irMapa(){
        ServicioBDDMemoria.nameSuperheroe = et_nombreSupInsertar.text.toString()
        ServicioBDDMemoria.single = obtenerValorRadioButton().toString()
        ServicioBDDMemoria.streghtForceLevel = et_fuerzaInsertar.text.toString()
        ServicioBDDMemoria.age = et_edadSupInsertar.text.toString()
        ServicioBDDMemoria.comicName = sp_comicsSuper.selectedItem.toString()

        val intentExplicito = Intent(
            this,
            Act_insertarLatitudLongitud::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun crearSuperheroe():Boolean{
        val url = urlPrincipal + "/Superheroe"
        var flag = true
        val listaDeParametros = obtenerDatos()
        url.httpPost(listaDeParametros).responseString {
                request, response, result ->
            when(result){
                is Result.Success ->{
                    val comicHttp = result.get()
                    Log.i("KLAXON", "SUPERHEROE CREADO: ${comicHttp}")
                    limpiarCajas()
                    flag = true
                }
                is Result.Failure ->{
                    val error = result.getException()
                    Log.i("KLAXON", "Error al crear SUPERHEROE: ${error.message}")
                    flag = false
                }
            }
        }
        return flag
    }
    fun obtenerDatos():List<Pair<String,String>>{

        latitudSup = intent.getDoubleExtra("Lat",0.00)
        longitudSup = intent.getDoubleExtra("Lng",0.00)

        if(latitudSup != null){
            Log.i("Intent", "La latitud es: ${latitudSup}")
        }

        val listaDeParametros = listOf(
            "nameSuperheroe" to et_nombreSupInsertar.text.toString(),
            "single" to obtenerValorRadioButton().toString(),
            "streghtForceLevel" to et_fuerzaInsertar.text.toString(),
            "age" to et_edadSupInsertar.text.toString(),
            "comicName"  to sp_comicsSuper.selectedItem.toString(),
            "latitud" to latitudSup.toString(),
            "longitud" to longitudSup.toString(),
            "imagenURL" to et_urlImagen.text.toString()
        )
        return listaDeParametros
    }
    fun obtenerValorRadioButton():Boolean{
        var single: Boolean = true
        if(rb_soltero.isChecked == true){
            return single
        }
        if (rb_casado.isChecked == true){
            single = false
            return single
        }
        return single
    }
    fun limpiarCajas(){
        et_nombreSupInsertar.setText("")
        et_edadSupInsertar.setText("")
        et_fuerzaInsertar.setText("")
        rb_casado.setChecked(false)
        rb_soltero.setChecked(false)

    }
    fun obtenerComic(): ArrayList<String> {
        val url = urlPrincipal + "/comic"
        var listaComics= arrayListOf<ComicMod>()
        var datos_nombre= arrayListOf<String>()
        var ultimo_dato= arrayListOf<String>()
        val peticion= url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val comics = Klaxon().parseArray<ComicHttp>(data)
                    if (comics != null) {
                        comics.forEach {
                            Log.i("http-klaxon", "Nombre:${it.nombreComic}")
                            listaComics.add(ComicMod(
                                it.nombreComic.toString(),
                                it.vigencia.toString(),
                                it.paginas.toString(),
                                it.precio.toString()))
                            Log.i("lista_servidor-dentro",listaComics.toString()
                            )
                        }
                    }
                    listaComics.forEach{
                        var nombreComic = it.nombreComic
                        datos_nombre.add(nombreComic)
                        Log.i("Comic",datos_nombre.toString())
                    }
                    ultimo_dato= datos_nombre
                    Log.i("Comic-ultimo",ultimo_dato.toString())
                }

                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http_klaxon", "error:${ex.message}")
                }
            }
        }
        peticion.join()
        Log.i("nombre_servidor",ultimo_dato.toString())
        return ultimo_dato
    }


}
