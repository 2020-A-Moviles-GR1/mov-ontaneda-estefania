package com.example.nicou

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_enviar_parametros.*
import kotlinx.android.synthetic.main.activity_main.*

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

        val leo = intent.getParcelableExtra<Mascota>("leo")
        if(leo != null){
            Log.i("parcelable", "${leo.nombre} ${leo.duenio?.nombre}")
        }

        val arregloMascotas = intent.getParcelableArrayListExtra<Mascota>("arregloMascotas")
        if(arregloMascotas != null){
            arregloMascotas.forEach {
                if(it != null){
                    Log.i("parcelable", "EN ARRAY :)")
                    Log.i("parcelable", "${it.nombre} ${it.duenio?.nombre}")
                }

            }

        }

        btn_devolver_respuesta.setOnClickListener {
            finish()
        }

        btn_resp_aceptar
            .setOnClickListener {
                val nombre = "Nico"
                val edad = 22
                val intentRespuesta = Intent()
                intentRespuesta.putExtra("nombre", nombre)
                intentRespuesta.putExtra("edad", edad)
                setResult(
                    RESULT_OK,
                    intentRespuesta
                )
                finish()
            }

        btn_resp_cancelar
            .setOnClickListener {
                val intentCancelado = Intent()
                setResult(
                    Activity.RESULT_CANCELED,
                    intentCancelado
                )
                finish()
            }
    }

}
