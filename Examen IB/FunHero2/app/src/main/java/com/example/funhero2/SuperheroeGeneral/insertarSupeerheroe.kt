package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_insertar_supeerheroe.*

class insertarSupeerheroe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_supeerheroe)

        var listaComics: Spinner =findViewById(R.id.sp_comicsSuper)

        var listaComicsMemoria = ServicioBDDMemoria.listaComic
        var datos_nombre:ArrayList<String> = arrayListOf()
        listaComicsMemoria.forEach{
            datos_nombre.add(it.nombreComic)
        }

        val adaptadordatos= ArrayAdapter(
            this,android.R.layout.simple_spinner_item, //nombre layout
            datos_nombre                            //lista
        )
        listaComics.setAdapter(adaptadordatos)

        btn_AgregarSuperheroe.setOnClickListener {
            obtenerDatos()
            Snackbar.make(it, "SUPERHEROE INSERTADO", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    /*
    * var nameSuperheroe: String,
                 var single: Boolean,
                 var streghtForceLevel: Double,
                 var age: Int,
                 var comicName: String
    * */

    fun obtenerDatos(){
        val nombreSuperheroe = et_nombreSupInsertar.text.toString()
        val single = obtenerValorRadioButton()
        val fuerzaSup: Double = et_fuerzaInsertar.text.toString().toDouble()
        val edadSup = et_edadSupInsertar.text.toString().toInt()
        val nombreComic = sp_comicsSuper.selectedItem.toString()
        ServicioBDDMemoria.añadirSuperheroe(nombreSuperheroe,single,fuerzaSup,edadSup,nombreComic)
        limpiarCajas()
    }

    fun obtenerValorRadioButton():Boolean{
        var single: Boolean = true
        if(rb_soltero.isChecked == true){
            return single
        }
        if (rb_casado.isChecked == true){
            single = false
            return single
        }
        return single
    }
    fun limpiarCajas(){
        et_nombreSupInsertar.setText("")
        et_edadSupInsertar.setText("")
        et_fuerzaInsertar.setText("")
        rb_casado.setChecked(false)
        rb_soltero.setChecked(false)

    }
}
