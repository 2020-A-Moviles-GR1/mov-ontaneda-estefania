package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria

class mostrarSuperheroes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_superheroes)
        var lista_Superheroe: ListView = findViewById(R.id.lv_mostrarSuperheroes)
        var listaSuperheroeMemoria : ArrayList<SuperheroeMod> = ServicioBDDMemoria.listaSuperheroe

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)
        lista_Superheroe.setAdapter(adaptador)
    }
}
