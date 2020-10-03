package com.example.gymvirtual.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymvirtual.R
import kotlinx.android.synthetic.main.activity_act__sexo.*

class Act_Sexo : AppCompatActivity() {
    var nombreCompartido: String ? = ""
    var edadCompartido: Int = 0
    var pesoCompartido : Double = 0.0
    var alturaCompartido:Double = 0.0
    var correoCompartido: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__sexo)

        /*if(correoCompartido != null){
            Log.i("Intent", "El correo del usuario es: ${correoCompartido}")
        }*/
        //abrirBienvenido()
        enviarSexo()
    }

    fun abrirBienvenido() {
        val intentExplicito = Intent(
            this,
            Act_Bienvenido::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarSexo() {
        val intentExplicito = Intent(
            this,
            Act_Bienvenido::class.java
        )
            iv_hombre.setOnClickListener {
                nombreCompartido = intent.getStringExtra("nombre")
                pesoCompartido = intent.getDoubleExtra("peso",0.0)
                edadCompartido = intent.getIntExtra("edad", 0)
                alturaCompartido = intent.getDoubleExtra("altura",0.0)
                correoCompartido = intent.getStringExtra("correo")

                intentExplicito.putExtra("nombre",nombreCompartido)
                intentExplicito.putExtra("edad", edadCompartido)
                intentExplicito.putExtra("peso",pesoCompartido)
                intentExplicito.putExtra("altura", alturaCompartido)
                intentExplicito.putExtra("correo", correoCompartido)
                intentExplicito.putExtra("sexo", "H")
                startActivity(intentExplicito)
            }
            iv_mujer.setOnClickListener {
                nombreCompartido = intent.getStringExtra("nombre")
                pesoCompartido = intent.getDoubleExtra("peso",0.0)
                edadCompartido = intent.getIntExtra("edad", 0)
                alturaCompartido = intent.getDoubleExtra("altura",0.0)
                correoCompartido = intent.getStringExtra("correo")

                intentExplicito.putExtra("nombre",nombreCompartido)
                intentExplicito.putExtra("edad", edadCompartido)
                intentExplicito.putExtra("peso",pesoCompartido)
                intentExplicito.putExtra("altura", alturaCompartido)
                intentExplicito.putExtra("correo", correoCompartido)
                intentExplicito.putExtra("sexo", "M")
                startActivity(intentExplicito)
            }

        }
    }

