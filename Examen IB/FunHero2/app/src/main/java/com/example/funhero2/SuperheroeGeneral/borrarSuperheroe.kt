package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.SuperheroeHttp
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_borrar_superheroe.*

class borrarSuperheroe : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrar_superheroe)
        var lista_Superheroe: ListView = findViewById(R.id.lv_superheroesEliminar)
        var listaSuperheroeMemoria = obtenerSuperheroe()
        val adaptador =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)
        lista_Superheroe.setAdapter(adaptador)

        lista_Superheroe.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("List-view", "Posicion: $position")
                btn_matarSuperheroe.setOnClickListener {
                    adaptador.notifyDataSetChanged()
                    Log.i("KLAXON", "SUPERHEROE ${listaSuperheroeMemoria}")
                    var nombre = listaSuperheroeMemoria.get(position).nameSuperheroe
                    var idSuperheroe = obteneridSuperheroe(nombre)
                    deleteSuperheroe(idSuperheroe)
                    listaSuperheroeMemoria.removeAt(position)
                    Snackbar.make(view, "SUPERHEROE MUERTO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
    }

    fun deleteSuperheroe(
        posicion: Int) {
        val url = urlPrincipal + "/superheroe" + "/" + posicion
        Log.i("url_put", url)
        url.httpDelete().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("KLAXON-Error", "El error al eliminar superheroe: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al eliminar: ${usuarioString}")
                }
            }
        }
    }

    fun obtenerSuperheroe(): ArrayList<SuperheroeMod>{
        val url = urlPrincipal + "/superheroe"
        var listaSuperheroes = arrayListOf<SuperheroeMod>()
        var peticion = url.httpGet().responseString { request, response, result ->
            when (result){
                is Result.Success ->{
                    val data = result.get()
                    Log.i("KLAXON-SUCESS", "SUPERHEROES EN SAILS:${data}")
                    val comics = Klaxon().parseArray<SuperheroeHttp>(data)
                    if(comics != null){
                        comics.forEach{
                            Log.i("KLAXON", "NOMBRE COMIC: ${it.nameSuperheroe}")
                            listaSuperheroes.add(SuperheroeMod(
                                it.nameSuperheroe.toString(),
                            it.single.toString(),
                            it.streghtForceLevel.toString(),
                            it.age.toString(),
                            it.comicName.toString()))
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
        return listaSuperheroes
    }

    fun obteneridSuperheroe(nombre: String):Int{
        val url = urlPrincipal + "/superheroe"
        var idSuperheroe = 0
        var peticion = url.httpGet().responseString { request, response, result ->
            when(result){
                is Result.Success ->{
                    val data = result.get()
                    Log.i("KLAXON", "DATA ${data}")
                    val superheroes = Klaxon().parseArray<SuperheroeHttp>(data)
                    if(superheroes != null){
                        superheroes.forEach {
                            Log.i("KLAXON", "ID: ${it.id}")
                            Log.i("ID", "ID: ${nombre}")
                            if(nombre == it.nameSuperheroe){
                                idSuperheroe= it.id
                                Log.i("ID", "ID: ${idSuperheroe}")
                            }
                        }
                    }
                }
                is Result.Failure ->{
                    val error = result.getException()
                    Log.i("Error", "ERROR: ${error}")
                }
            }
        }
        peticion.join()
        return idSuperheroe
    }
}
