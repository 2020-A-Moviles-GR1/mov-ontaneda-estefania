package com.example.funhero2.ComicGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_insertar_comic.*

class InsertarComic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_comic)

        var listaComic = arrayListOf<ComicMod>()

        btn_agregarComic.setOnClickListener {
            obtenerDatos()
            Snackbar.make(it, "COMIC INSERTADO", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    fun validacionesText(campo: String ): Boolean{
        if (campo != ""){
            return true
        }
        return false
    }

    fun limpiarCajas(){
        et_nombreComic.setText("")
       // et_vigenciaComic.setText("")
        et_pagsComic.setText("")
        et_precioComic.setText("")
    }

    fun obtenerValorSpinner():Boolean{
        val vigComic = spinner.selectedItem.toString()
        if(vigComic.equals("Vigente")){
            return true
        }else{
            return false
        }
        return true
    }

    fun obtenerDatos(){
        val nombreComic = et_nombreComic.text.toString()
        val vigComic = obtenerValorSpinner()
        val pagComic = et_pagsComic.text.toString().toInt()
        val precioComic = et_precioComic.text.toString().toDouble()
        ServicioBDDMemoria.añadirComic(
            nombreComic,
            vigComic,
            pagComic,
            precioComic
        )
        limpiarCajas()
    }
}
