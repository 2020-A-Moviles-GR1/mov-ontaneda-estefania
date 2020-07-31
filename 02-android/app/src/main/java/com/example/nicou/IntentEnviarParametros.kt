package com.example.nicou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_enviar_parametros.*

class IntentEnviarParametros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_intent_enviar_parametros)
        val numeroEncontrado = intent.getIntExtra("numero", 0)
        if(numeroEncontrado != null){
            Log.i("intent", "El número encontrado es: ${numeroEncontrado}")
        }

        val TextoCompartido : String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(TextoCompartido != null){
            Log.i("intent", "El texto es: ${TextoCompartido}")
        }

        btn_devolver_respuesta.setOnClickListener {
            finish()
        }
    }

}
