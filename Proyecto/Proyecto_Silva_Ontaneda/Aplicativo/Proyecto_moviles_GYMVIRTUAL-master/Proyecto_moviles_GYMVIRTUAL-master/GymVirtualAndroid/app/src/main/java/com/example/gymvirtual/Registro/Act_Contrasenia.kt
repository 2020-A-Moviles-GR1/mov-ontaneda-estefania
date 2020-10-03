package com.example.gymvirtual.Registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymvirtual.Login.Act_edad_registro
import com.example.gymvirtual.Login.Act_peso_registro
import com.example.gymvirtual.R
import kotlinx.android.synthetic.main.activity_act__contrasenia.*
import kotlinx.android.synthetic.main.activity_act_edad_registro.*

class Act_Contrasenia : AppCompatActivity() {
    var nombreCompartido: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__contrasenia)
        btn_contrasenia.setOnClickListener {
            enviarEdad()
        }
    }

    fun enviarEdad(){
        val intentExplicito = Intent(
            this,
            Act_edad_registro::class.java
        )
        nombreCompartido = intent.getStringExtra("nombre")
        intentExplicito.putExtra("nombre",nombreCompartido )
        startActivity(intentExplicito)
    }
}