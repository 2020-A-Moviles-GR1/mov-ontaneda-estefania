package com.example.gymvirtual.Ejercicio

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import com.example.gymvirtual.Login.Act_Nombre_Registro
import com.example.gymvirtual.Modelo.EjercicioHttp
import com.example.gymvirtual.R
import kotlinx.android.synthetic.main.activity_act__ejer_cronometro.*


class  Act_EjerCronometro : AppCompatActivity() {

    lateinit var progressBar: ProgressBar
    lateinit var progressText: TextView
    lateinit var tituloEjercicio: TextView
    lateinit var iv_imagenEjercicio: ImageView
    var listaImagenes = arrayListOf<String>()
    var listaTitulos = arrayListOf<String>()
    var contador = 0
    var indexImagenes = 0
    val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__ejer_cronometro)

        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.progress_text)
        tituloEjercicio = findViewById(R.id.tv_tiempoTituloEjercicio)
        iv_imagenEjercicio = findViewById(R.id.iv_imagenEjercicio)

        reiniciarCronometro()
        val arregloEjercicios = intent.getParcelableArrayListExtra<EjercicioHttp>("arregloEjercicios")
        if(arregloEjercicios != null){
            arregloEjercicios.forEach {
                if(it != null){
                        listaImagenes.add(it.url_Imagen.toString())
                        listaTitulos.add(it.nombre_ejercicio.toString())
                        ponerTitulosImagen(listaTitulos,listaImagenes)
                        btn_siguienteEjercicio.setOnClickListener { iterador: View ->

                            contador += 1
                            if(contador == 3){
                                abrirEjercicioCompleto()
                            }
                            indexImagenes = indexImagenes + 1
                                ponerTitulosImagen(listaTitulos,listaImagenes)
                                reiniciarCronometro()
                            if (it.url_ejercicio != ""){
                                iv_imagenEjercicio.setOnClickListener {iterador2 ->
                                    Log.i("Youtube", "url: ${it.url_ejercicio}")
                                    var uri: Uri = Uri.parse(it.url_ejercicio)
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    startActivity(intent)
                                }
                            }
                        }
                }
            }
        }
    }

    fun ponerTitulosImagen(listaTitulos: ArrayList<String>, listaImagenes: ArrayList<String>){
        listaImagenes.forEach {
            Glide.with(this)
                .asBitmap()
                .load(listaImagenes[indexImagenes])
                .into(iv_imagenEjercicio)
        }
        listaTitulos.forEach {
            tv_tiempoTituloEjercicio.setText(listaTitulos[indexImagenes])
        }
    }

    fun reiniciarCronometro(){
        var i = 0
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (i <= 100) {
                    progressText.text = "" + i
                    progressBar.progress = i
                    i++
                    handler.postDelayed(this, 200)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }, 200)
        i = 0
    }

    fun abrirEjercicioCompleto(){
        val intentExplicito = Intent(
            this,
            Act_Ejercicio_Completo::class.java
        )
        startActivity(intentExplicito)
    }

}