package com.example.nicou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaEntrenadores = arrayListOf<Entrenador>()

        listaEntrenadores.add(Entrenador("Nico", "Ontaneda"))
        listaEntrenadores.add(Entrenador("Miguel", "Bustamante"))
        listaEntrenadores.add(Entrenador("Gabriel", "Perez"))
        listaEntrenadores.add(Entrenador("Gonzalo", "Merino"))
        listaEntrenadores.add(Entrenador("Ivan", "Sarzosa"))
        listaEntrenadores.add(Entrenador("Myzraim", "Garofalo"))
        listaEntrenadores.add(Entrenador("Lili", "Ontaneda"))

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaEntrenadores
        )

        lv_numeros.adapter = adaptador

        lv_numeros.
        onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            Log.i("List-view", "Posicion: $position")
        }

        btn_anadir_entrenador.setOnClickListener {
            anadirEntrenador(adaptador,listaEntrenadores)
        }


    }

    fun anadirEntrenador(
        adaptador: ArrayAdapter<Entrenador>,
        listaEntrenadores: ArrayList<Entrenador>)
    {
        listaEntrenadores.add(Entrenador("Esteban", "Galarza"))
        adaptador.notifyDataSetChanged()
    }

}

