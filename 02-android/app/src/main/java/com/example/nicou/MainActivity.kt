package com.example.nicou

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_ciclo_vida.setOnClickListener { boton ->
            irCicloDeVida()
        }
    //Boton list view
        btn_list_view.setOnClickListener { boton ->
            irListView()
        }

        btn_intent_respuesta.setOnClickListener {
            //irIntentEnviarParametrosConRespuesta()
            irAIntentConRespuesta()
        }

        btn_intent_implicito.setOnClickListener {
            enviarIntentRespuesta()
        }

        btn_resp_propia
            .setOnClickListener {
                enviarIntentConRespuestaPropia()
            }

        btn_http.setOnClickListener{
            abrirActividadHttp()
        }

        btn_recycler.setOnClickListener {
            abrirRecyclerViewActivity()
        }

        btn_mapa.setOnClickListener {
            abrirMapaActivity()
        }
    }

    fun abrirRecyclerViewActivity(){
        val intentExplicito = Intent(
            this,
            RecyclerViewActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun abrirMapaActivity(){
        val intentExplicito = Intent(
            this,
            MapsActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarIntentConRespuestaPropia() {
        val intentExplicito = Intent(
            this,
            IntentEnviarParametros::class.java
        )
        startActivityForResult(intentExplicito, 305)
    }

    fun abrirActividadHttp(){
        val intentExplicito = Intent(
            this,
            HttpActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun enviarIntentRespuesta(){
        val intentConRespuesta = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        startActivityForResult(intentConRespuesta,340)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode){
            Activity.RESULT_OK ->{
                Log.i("resultado", "OKI :D <3 ")
                when (requestCode){
                    304 -> {
                        val uri = data?.data
                        if (uri != null) {
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado", "Telefono: ${telefono}")
                        }
                    }

                    305 -> {
                        if (data!= null){
                            val nombre = data.getStringExtra("nombre")
                            val edad = data.getIntExtra("edad", 0)
                            Log.i("resultado", "Nombre: ${nombre} Edad: ${edad}")
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED -> {
                Log.i("resultado", "FAIL :(")
            }
        }

    }
    fun irCicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irListView(){
        val intentExplicito = Intent(
            this,
            ListViewActivity::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irAIntentConRespuesta() {
        val intentExplicito = Intent(
            this,
            IntentEnviarParametros::class.java
        )
        intentExplicito.putExtra("numero", 2)

        val nico = Usuario(
            "Nico",
            22,
            Date(),
            10.0
        )

        val leo = Mascota(
            "Leo",
            nico
        )

        val arregloMascotas = arrayListOf<Mascota>(leo)
        intentExplicito.putExtra("leo", leo)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)

        startActivity(intentExplicito)
    }

    fun irIntentEnviarParametrosConRespuesta(){
        val intentExplicito = Intent(
            this,
            IntentEnviarParametros::class.java
        )
        intentExplicito.putExtra("numero", 10)
        this.startActivity(intentExplicito)
    }
}
