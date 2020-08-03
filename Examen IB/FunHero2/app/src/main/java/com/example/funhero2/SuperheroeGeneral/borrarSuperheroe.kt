package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import kotlinx.android.synthetic.main.activity_borrar_superheroe.*
import kotlinx.android.synthetic.main.fragment_gallery.*

class borrarSuperheroe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrar_superheroe)

        var lista_Superheroe: ListView = findViewById(R.id.lv_superheroesEliminar)
        var listaSuperheroeMemoria = ServicioBDDMemoria.listaSuperheroe

        val adaptador =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)
        lista_Superheroe.setAdapter(adaptador)

        lista_Superheroe.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("List-view", "Posicion: $position")

                btn_matarSuperheroe.setOnClickListener {
                    listaSuperheroeMemoria.removeAt(position)
                    adaptador.notifyDataSetChanged()
                }
            }
    }
}
