package com.example.gymvirtual.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymvirtual.R
import kotlinx.android.synthetic.main.activity_act__correo_login.*

class Act_CorreoLogin : AppCompatActivity() {
    var nombreCompartido: String ? = ""
    var edadCompartido: Int = 0
    var pesoCompartido : Double = 0.0
    var alturaCompartido:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__correo_login)

        /*if(alturaCompartido != null){
            Log.i("Intent", "La altura del usuario es: ${alturaCompartido}")
        }*/
        btn_correoSig.setOnClickListener {
           //abrirSexoLogin()
            enviarCorreo()
        }
    }

    fun abrirSexoLogin(){
        val intentExplicito = Intent(
            this,
            Act_Sexo::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarCorreo(){
        val intentExplicito = Intent(
            this,
            Act_Sexo::class.java
        )
        nombreCompartido = intent.getStringExtra("nombre")
        pesoCompartido = intent.getDoubleExtra("peso",0.0)
        edadCompartido = intent.getIntExtra("edad", 0)
        alturaCompartido = intent.getDoubleExtra("altura",0.0)

        intentExplicito.putExtra("nombre",nombreCompartido)
        intentExplicito.putExtra("edad", edadCompartido)
        intentExplicito.putExtra("peso",pesoCompartido)
        intentExplicito.putExtra("altura", alturaCompartido)
        intentExplicito.putExtra("correo", et_correoLogin.text.toString())
        startActivity(intentExplicito)
    }

}
