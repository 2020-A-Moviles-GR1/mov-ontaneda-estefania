package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.SuperheroeHttp
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class buscarSuperheroe : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_superheroe)

        var lista_Superheroe: ListView = findViewById(R.id.lv_superheroesBuscar)
        var listaSuperheroeMemoria = obtenerSuperheroes()
        var nomSuperheroe : EditText = findViewById(R.id.et_nomSupBuscar)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)

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

        lista_Superheroe.setAdapter(adaptador)
        nomSuperheroe.addTextChangedListener(mSearchTw)
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
