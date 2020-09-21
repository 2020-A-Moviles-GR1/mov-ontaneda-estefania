package com.example.funhero2.SuperheroeGeneral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funhero2.Maps.Act_MapaAllSuperheroes
import com.example.funhero2.R
import kotlinx.android.synthetic.main.activity_insertar_comic.*
import kotlinx.android.synthetic.main.activity_menu_principal_superheroe.*

class menuPrincipalSuperheroe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal_superheroe)

        btn_insertarSup.setOnClickListener {
            irInsertarSup()
        }

        btn_editarSup.setOnClickListener{
            irEditarSup()
        }

        btn_eliminarSup.setOnClickListener {
            irEliminarSup()
        }

        btn_buscarSup.setOnClickListener {
            irBuscarSup()
        }

        btn_mostrarSup.setOnClickListener {
            irMostrarSup()
        }

        btn_heroesVillanos.setOnClickListener{
            irMapaSuperheroes()
        }
    }

    fun irInsertarSup(){
        val intentExplicito = Intent(
            this,
            insertarSupeerheroe::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irEditarSup(){
        val intentExplicito = Intent(
            this,
            actualizarSuperheroe::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irEliminarSup(){
        val intentExplicito = Intent(
            this,
            borrarSuperheroe::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irBuscarSup(){
        val intentExplicito = Intent(
            this,
            buscarSuperheroe::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irMostrarSup(){
        val intentExplicito = Intent(
            this,
            mostrarSuperheroes::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irMapaSuperheroes(){
        val intentExplicito = Intent(
            this,
            Act_MapaAllSuperheroes::class.java
        )
        this.startActivity(intentExplicito)
    }

}
