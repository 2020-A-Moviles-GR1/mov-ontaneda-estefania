package com.example.gymvirtual.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymvirtual.R
import com.example.gymvirtual.Registro.Act_Contrasenia
import kotlinx.android.synthetic.main.activity_act__nombre__registro.*

class Act_Nombre_Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__nombre__registro)
        btn_nombre.setOnClickListener(){
           // enviarNombre()
            enviarNombreaContrasenia()
        }
    }
    fun abriredad(){
        val intentExplicito = Intent(
            this,
            Act_edad_registro::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarNombre(){
        val intentExplicito = Intent(
            this,
            Act_edad_registro::class.java
        )
        intentExplicito.putExtra("nombre", et_nombre.text.toString())
        startActivity(intentExplicito)
    }

    fun enviarNombreaContrasenia(){
        val intentExplicito = Intent(
            this,
            Act_Contrasenia::class.java
        )
        intentExplicito.putExtra("nombre", et_nombre.text.toString())
        startActivity(intentExplicito)
    }
}