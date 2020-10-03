package com.example.gymvirtual.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymvirtual.R
import kotlinx.android.synthetic.main.activity_act_peso_registro.*

class Act_peso_registro : AppCompatActivity() {
    var nombreCompartido: String ? = ""
    var edadCompartido: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_peso_registro)


        /*if(edadCompartido != null){
            Log.i("Intent", "La edad del usuario es: ${edadCompartido}")
        }*/


       /* if(nombreCompartido != null){
            Log.i("Intent", "El nombre de usuario es: ${nombreCompartido}")
        }*/

        btn_peso.setOnClickListener{
            enviarPeso()
        }
    }

    fun abrirAltura(){
        val intentExplicito = Intent(
            this,
            Act_AlturaLogin::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarPeso(){
        val intentExplicito = Intent(
            this,
            Act_AlturaLogin::class.java
        )
        nombreCompartido = intent.getStringExtra("nombre")
        edadCompartido = intent.getIntExtra("edad", 0)

        intentExplicito.putExtra("nombre",nombreCompartido)
        intentExplicito.putExtra("edad", edadCompartido)
        intentExplicito.putExtra("peso",et_peso.text.toString().toDouble())
        startActivity(intentExplicito)
    }
}