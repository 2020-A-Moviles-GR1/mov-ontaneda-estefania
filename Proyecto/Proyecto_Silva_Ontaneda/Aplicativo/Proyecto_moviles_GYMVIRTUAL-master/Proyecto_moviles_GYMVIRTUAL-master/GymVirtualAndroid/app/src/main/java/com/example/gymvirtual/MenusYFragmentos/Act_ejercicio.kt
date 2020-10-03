package com.example.gymvirtual.MenusYFragmentos

import Modelo.ServicioBDDMemoria
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.example.gymvirtual.Adaptadores.adaptadorRecyclerView2
import com.example.gymvirtual.Ejercicio.Act_EjerCronometro
import com.example.gymvirtual.Modelo.EjercicioHttp
import com.example.gymvirtual.Modelo.RutinaHttp
import com.example.gymvirtual.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_act_ejercicio.*

class Act_ejercicio : AppCompatActivity() {
    var arrayImagenesURL = arrayListOf<String>()
    var arrayTitulos= arrayListOf<String>()
    var arrayTiempos =  arrayListOf<String>()

    val urlPrincipal = "http://192.168.1.4:1337"
    var arrayRutina = arrayListOf<EjercicioHttp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_ejercicio)
        arrayRutina = arrayListEjercicioRutina()
        initImageBitmaps(arrayRutina)
        btn_comenzarEntrenamiento.setOnClickListener {
            irComenzarEjercicios()
        }
    }

    fun initImageBitmaps(arrayRutina: ArrayList<EjercicioHttp>){
        arrayRutina.forEach {
            arrayImagenesURL.add(it.url_Imagen.toString())
            arrayTitulos.add(it.nombre_ejercicio.toString())
            arrayTiempos.add(it.tiempo.toString())
        }
        initRecyclerView()
    }

    fun initRecyclerView(){
        var recyclerView: RecyclerView = findViewById(R.id.rv_ejercicio)
        var adaptador = adaptadorRecyclerView2(this,arrayImagenesURL,arrayTitulos,arrayTiempos)
        recyclerView.setAdapter(adaptador)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun arrayListEjercicioRutina():ArrayList<EjercicioHttp>{
        val url = urlPrincipal + "/Rutina"
        val ejercicioHttpArray = arrayListOf<EjercicioHttp>()
        var peticion = url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val rutinas = Klaxon().converter(ServicioBDDMemoria.convertidorRutina()).parseArray<RutinaHttp>(data)
                        if (rutinas != null) {
                            rutinas.forEach {
                                it.ejercicios!!.forEach {
                                    ejercicioHttpArray.add(EjercicioHttp(it.id, it.nombre_ejercicio,
                                        it.url_ejercicio,it.url_Imagen,it.tiempo,it.rutina))
                                }
                            }
                        }
                    }
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("Error", "ERROR: ${error}")
                    }
                }
            }
        peticion.join()
        return ejercicioHttpArray
    }

    fun irComenzarEjercicios(){
        val intentExplicito = Intent(this, Act_EjerCronometro::class.java)
        val arrayEjercicioHttp = arrayListEjercicioRutina()
        intentExplicito.putExtra("arregloEjercicios", arrayEjercicioHttp)
        startActivity(intentExplicito)
    }
}