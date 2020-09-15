package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.SuperheroeHttp
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class mostrarSuperheroes : AppCompatActivity() {

    val urlPrincipal = "http://192.168.1.4:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_superheroes)

        var lista_Superheroe: ListView = findViewById(R.id.lv_mostrarSuperheroes)

        var listaSuperheroeMemoria  = obtenerSuperheroes()

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)
        lista_Superheroe.setAdapter(adaptador)
    }

    fun obtenerSuperheroes(): ArrayList<SuperheroeMod>{
        val url = urlPrincipal + "/superheroe"
        var listaSuperheroe = arrayListOf<SuperheroeMod>()
        var peticion = url.httpGet().responseString { request, response, result ->
            when (result){
                is Result.Success ->{
                    val data = result.get()
                    Log.i("KLAXON-SUCESS", "SUPERHEROES EN SAILS:${data}")
                    val superheroes = Klaxon().parseArray<SuperheroeHttp>(data)
                    if(superheroes != null){
                        superheroes.forEach{
                            Log.i("KLAXON", "NOMBRE SUPERHEROE: ${it.nameSuperheroe}")
                            //listaComics.add(ComicMod(it.nombreComic.toString(),it.vigencia.toString(),it.paginas.toString(),it.precio.toString()))
                            listaSuperheroe.add(SuperheroeMod(it.nameSuperheroe.toString(),it.single.toString(),it.streghtForceLevel.toString(),it.age.toString(),it.comicName.toString()))
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
        return listaSuperheroe
    }
}
