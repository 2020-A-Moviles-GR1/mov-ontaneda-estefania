package com.example.nicou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_http.*
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        btn_obtener.setOnClickListener{
            obtenerUsuarios()
            obtenerPokemons()
        }

        btn_crear.setOnClickListener {
            crearUsuarios()
        }
    }

    fun crearUsuarios(){
        val url = urlPrincipal + "/Usuario"

         //val parametrosUsuario:List<Pair<String,String>> = listOf( -->Es una lista con pares

        val parametrosUsuario= listOf(
            "cedula" to "1708102486",
             "nombre" to "Lili",
             "correo" to "liliont@gmail.com",
            "estadoCivil" to "Soltero",
             "password" to "Passwor0RD"
         )

        url.
        httpPost(parametrosUsuario)
            .responseString { request, response, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon", "Error de crear usuario: ${error.message}")
                    }
                    is Result.Success -> {
                        //Dependiendo del servidor vamos a tener diferentes respuestas
                        val usuarioHttp = result.get()
                        Log.i("http-klaxon", "Usuario creado: ${usuarioHttp}")
                    }
                }
            }
    }

    fun obtenerUsuarios() {
        /*val pokemonString = """
            {
            "createdAt": 1597671572492,
            "updatedAt": 1597890175293,
            "id": 1,
            "nombre": "Pikachu",
            "usuario": 1,
            "batalla": 1
    }""".trimIndent()*/
        //val pokemonInstancia = Klaxon().parse<PokemonHttp>(pokemonString)
        /*if(pokemonInstancia != null){
            Log.i("http-klaxon", "Nombre: ${pokemonInstancia.fechaCreacion}")
        }*/

        val url = urlPrincipal + "/Usuario"
        url.httpGet().responseString {
                request, response, result ->
            when (result){
                is Result.Success -> {
                    val data = result.get()
                    //Log.i("http-klaxon", "Data: ${data}")
                    val usuarios =Klaxon().converter(Conversores.convertidorUsuario()).parseArray<UsuarioHttp>(data)
                    if(usuarios != null){
                        usuarios.forEach{
                            Log.i("KLAXON CONVERSOR", "USUARIO CONVERSOR: ${it}")
                                it.pokemons!!.forEach{
                                    Log.i("KLAXON CONVERSOR", "POKEMON CONVERSOR: ${it}")

                            }
                        }
                    }
                }
                is Result.Failure ->{
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
            }
        }
    }

    fun obtenerPokemons(){
        val urlPokemon = urlPrincipal + "/pokemon"
        urlPokemon.httpGet().responseString { request, response, result ->
            when (result){
                is Result.Success -> {
                    val data = result.get()
                    val pokemons =Klaxon().converter(Conversores.convertidorPokemon()).parseArray<PokemonHttp>(data)
                    if(pokemons!!.size != 0){
                        pokemons!!.forEach{
                            Log.i("KLAXON CONVERSOR", "POKEMON CONVERSOR: ${it.usuario.toString()}")
                        }
                    }
                }
                is Result.Failure ->{
                    val ex = result.getException()
                    Log.i("http-klaxon", "Error: ${ex.message}")
                }
            }
        }
    }
}
