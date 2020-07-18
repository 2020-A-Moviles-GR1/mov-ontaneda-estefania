package com.example.nicou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_ciclo_vida.*
import kotlinx.android.synthetic.main.activity_list_view.*
import java.util.ArrayList

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val listaEntrenador = arrayListOf<Entrenador>()
        listaEntrenador.add(Entrenador("Nico", "Ontaneda"))
        listaEntrenador.add(Entrenador("Miguel", "Bustamante"))
        listaEntrenador.add(Entrenador("Gabriel", "Perez"))
        listaEntrenador.add(Entrenador("Gonzalo", "Merino"))
        listaEntrenador.add(Entrenador("Ivan", "Sarzosa"))
        listaEntrenador.add(Entrenador("Myzraim", "Garofalo"))
        listaEntrenador.add(Entrenador("Lili", "Ontaneda"))

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaEntrenador
        )

        lv_numeros.adapter = adaptador

        lv_numeros.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            Log.i("List-view", "Posicion: "+ position)
        }

    }
}
