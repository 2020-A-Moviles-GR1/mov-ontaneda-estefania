package com.example.gymvirtual.Login

import Modelo.ServicioBDDMemoria
import Modelo.UsuarioHttp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.beust.klaxon.Klaxon
import com.example.gymvirtual.Menus.Actv_menu_principal
import com.example.gymvirtual.Modelo.EjercicioHttp
import com.example.gymvirtual.Modelo.RutinaHttp
import com.example.gymvirtual.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_act__bienvenido.*

class Act_Bienvenido : AppCompatActivity() {
    var nombreCompartido: String? = ""
    var edadCompartido: Int = 0
    var pesoCompartido : Double = 0.0
    var alturaCompartido:Double = 0.0
    var correoCompartido: String?= ""
    var sexoCompartido: String? = ""
    val urlGeneral = "http://192.168.1.4:1337"
    var arrayRutina = arrayListOf<UsuarioHttp>()

    override fun onCreate(savedInstanceState: Bundle?) {

        var topAnimation: Animation
        var bottomAnimation: Animation
        var imagenMov : LottieAnimationView
        var textoBienvenido: ImageView
        var btn_continuar: Button
        var facebook: ImageView
        var instagram: ImageView
        var twitter: ImageView
        var youtube: ImageView

        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_act__bienvenido)
        sexoCompartido = intent.getStringExtra("sexo")
        nombreCompartido = intent.getStringExtra("nombre")
        pesoCompartido = intent.getDoubleExtra("peso",0.0)
        edadCompartido = intent.getIntExtra("edad", 0)
        alturaCompartido = intent.getDoubleExtra("altura",0.0)
        correoCompartido = intent.getStringExtra("correo")

        val url = urlGeneral + "/usuario"
        val parametrosUsuario: List<Pair<String, String>> = listOf(
            "nombre_usu" to nombreCompartido.toString(),
            "edad_usu" to edadCompartido.toString(),
            "peso_usu" to pesoCompartido.toString(),
            "altura_usu" to alturaCompartido.toString(),
            "correo_usu" to correoCompartido.toString(),
            "sexo_usu" to sexoCompartido.toString(),
            "contrasenia_usu" to "123"
        )

        url.httpPost(parametrosUsuario).responseString { request, response, result ->
            when(result){
                is Result.Failure -> {
                 val error = result.getException()
                    Log.i("Error", "El error al crear usuario es: ${error}")
                }
                is Result.Success ->{
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al crear usuario es: ${usuarioString}")
                }
            }
        }
        tv_nombreBienvenido.text = nombreCompartido.toString()
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        imagenMov  = findViewById(R.id.ilottie_gimnasio)
        textoBienvenido = findViewById(R.id.iv_bienvenido)
        btn_continuar = findViewById(R.id.btn_comenzar)
        facebook = findViewById(R.id.iv_facebook)
        instagram = findViewById(R.id.iv_instagram)
        twitter = findViewById(R.id.iv_twitter)
        youtube = findViewById(R.id.iv_youtube)
        imagenMov.setAnimation(topAnimation)
        textoBienvenido.setAnimation(bottomAnimation)
        btn_continuar.setAnimation(bottomAnimation)
        facebook.setAnimation(bottomAnimation)
        instagram.setAnimation(topAnimation)
        twitter.setAnimation(bottomAnimation)
        youtube.setAnimation(topAnimation)

        btn_comenzar.setOnClickListener {
            abrirmenu()
        }
    }

    fun arrayListEjercicioRutina():ArrayList<UsuarioHttp>{
        val url = urlGeneral + "/Usuario"
        val usuarioHttpArray = arrayListOf<UsuarioHttp>()
        var peticion = url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                        if (usuarios != null) {
                            usuarios.forEach {
                                if(it.nombre_usu.equals(nombreCompartido)){
                                    usuarioHttpArray.add(UsuarioHttp(it.id,it.nombre_usu,it.edad_usu,it.peso_usu,it.altura_usu,it.correo_usu,it.sexo_usu))
                                    Log.i("Usuario", "Usuario ${usuarioHttpArray}")
                                }
                            }
                        }
                    }
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("Error", "ERROR: ${error}")
                    }
                }
            }
        peticion.join()
        return usuarioHttpArray
    }
    fun abrirmenu(){
        val intentExplicito = Intent(this, Actv_menu_principal::class.java)
        startActivity(intentExplicito)
    }
}
