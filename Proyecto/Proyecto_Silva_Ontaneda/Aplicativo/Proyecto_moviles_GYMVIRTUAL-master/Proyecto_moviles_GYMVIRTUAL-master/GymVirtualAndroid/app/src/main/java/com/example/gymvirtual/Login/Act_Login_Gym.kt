package com.example.gymvirtual.Login

import Modelo.UsuarioHttp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.beust.klaxon.Klaxon
import com.example.gymvirtual.Menus.Actv_menu_principal
import com.example.gymvirtual.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_login__gym.*

class Act_Login_Gym : AppCompatActivity() {
    lateinit var usuario:EditText
    lateinit var password:EditText
    lateinit var iniciar:Button
    val urlPrincipal = "http://192.168.1.4tr55:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__gym)
        usuario=findViewById(R.id.txtusuario)
        password=findViewById(R.id.txtcontrasena)
        iniciar=findViewById(R.id.btningresar)
        btningresar.setOnClickListener{
            var nombres= obtenerUusarios()
            val nombre = txtusuario.text
            nombres.forEach {
                if(it == nombre.toString()){
                    Log.i("usuario encontrado",usuario.toString())
                    val i=Intent(this,Actv_menu_principal::class.java)
                    startActivity(i)
                }
            }
        }
    }

    fun obtenerUusarios(): ArrayList<String> {
        val url = urlPrincipal + "/usuario"
        val nombre= arrayListOf<String>()
        var peticion= url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                    if (usuarios != null) {
                        usuarios.forEach {
                            nombre.add(it.nombre_usu.toString())
                            Log.i("Usuario", "Nombres Usuario ${nombre}")
                        }
                    }
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http_klaxon", "error:${ex.message}")
                }
            }
        }
        peticion.join()
        return nombre
    }
}