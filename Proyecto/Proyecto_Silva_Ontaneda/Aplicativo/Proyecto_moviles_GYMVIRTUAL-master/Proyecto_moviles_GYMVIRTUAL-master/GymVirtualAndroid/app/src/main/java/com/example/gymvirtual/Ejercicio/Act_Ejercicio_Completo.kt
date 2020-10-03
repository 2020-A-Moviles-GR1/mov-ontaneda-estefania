package com.example.gymvirtual.Ejercicio

import Modelo.ServicioBDDMemoria
import Modelo.UsuarioHttp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.example.gymvirtual.Adaptadores.AdaptadorEjerciciosCompletos
import com.example.gymvirtual.Adaptadores.adaptadorRecyclerView2
import com.example.gymvirtual.Menus.Actv_menu_principal
import com.example.gymvirtual.Modelo.EjercicioHttp
import com.example.gymvirtual.Modelo.RutinaHttp
import com.example.gymvirtual.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_act_ejercicio_completo.*

class Act_Ejercicio_Completo : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"

    var arrayImagenesURL = arrayListOf<String>()
    var arrayTitulos= arrayListOf<String>()
    var arrayTiempos =  arrayListOf<String>()

    var arrayRutina = arrayListOf<EjercicioHttp>()
    var arrayUsuario = arrayListOf<UsuarioHttp>()

    var idRutina = 0
    var idUsuario = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_ejercicio_completo)
        arrayRutina = arrayListEjercicioRutina()
        arrayUsuario = arrayListUsuario()

        arrayUsuario.forEach {
            idUsuario = it.id
        }

        initImageBitmaps(arrayRutina)
        btn_finalizarEntrenamiento.setOnClickListener {
            postUsuarioRutina(idRutina,idUsuario)
            abrirmenu()
        }
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
                                idRutina = it.id
                                it.ejercicios!!.forEach {
                                    ejercicioHttpArray.add(
                                        EjercicioHttp(it.id, it.nombre_ejercicio,
                                        it.url_ejercicio,it.url_Imagen,it.tiempo,it.rutina)
                                    )
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

    fun arrayListUsuario():ArrayList<UsuarioHttp>{
        val url = urlPrincipal + "/Usuario"
        val usuarioHttpArray = arrayListOf<UsuarioHttp>()
        var peticion = url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                        if (usuarios != null) {
                            usuarios.forEach {
                                    usuarioHttpArray.add(UsuarioHttp(it.id,it.nombre_usu,it.edad_usu,it.peso_usu,it.altura_usu,it.correo_usu,it.sexo_usu))
                                    Log.i("Usuario", "Usuario ${usuarioHttpArray}")
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
        return usuarioHttpArray
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
        var recyclerView: RecyclerView = findViewById(R.id.rv_ejercicioCompletos)
        var adaptador = AdaptadorEjerciciosCompletos(this,arrayImagenesURL,arrayTitulos,arrayTiempos)
        recyclerView.setAdapter(adaptador)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun postUsuarioRutina(idRutina: Int, idUsuario: Int){
        val url = urlPrincipal + "/UsuarioRutina"
        val parametrosUsuario: List<Pair<String, Int>> = listOf(
            "usuario" to idUsuario,
            "rutina" to idRutina
        )
        url.httpPost(parametrosUsuario).responseString { request, response, result ->
            when(result){
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("Error", "El error al crear usuario es: ${error}")
                }
                is Result.Success ->{
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al crear usuario es: ${usuarioString}")
                }
            }
        }
    }

    fun abrirmenu(){
        val intentExplicito = Intent(this, Actv_menu_principal::class.java)
        startActivity(intentExplicito)
    }
}