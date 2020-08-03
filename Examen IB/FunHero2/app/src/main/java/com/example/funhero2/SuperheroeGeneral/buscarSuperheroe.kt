package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria

class buscarSuperheroe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_superheroe)

        var lista_Superheroe: ListView = findViewById(R.id.lv_superheroesBuscar)
        var listaSuperheroeMemoria : ArrayList<SuperheroeMod> =
            ServicioBDDMemoria.listaSuperheroe
        var nomSuperheroe : EditText = findViewById(R.id.et_nomSupBuscar)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)

        var mSearchTw = object : TextWatcher {

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                adaptador.getFilter().filter(s)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }

        lista_Superheroe.setAdapter(adaptador)
        nomSuperheroe.addTextChangedListener(mSearchTw)
    }
}
