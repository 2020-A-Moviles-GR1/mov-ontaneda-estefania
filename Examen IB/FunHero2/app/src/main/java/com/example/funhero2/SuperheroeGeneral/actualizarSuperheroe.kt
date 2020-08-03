package com.example.funhero2.SuperheroeGeneral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.funhero2.Modelo.SuperheroeMod
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_actualizar_superheroe.*

class actualizarSuperheroe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_superheroe)

        var lista_Superheroes: ListView = findViewById(R.id.lv_superheroesAct)
        var listaSuperheroeMemoria: ArrayList<SuperheroeMod> = ServicioBDDMemoria.listaSuperheroe
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaSuperheroeMemoria)

        lista_Superheroes.setAdapter(adaptador)

        lista_Superheroes.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("List-view", "Posicion: $position")
                var superheroeDeseado = listaSuperheroeMemoria[position]
                val nombreSuper = superheroeDeseado.nameSuperheroe
                val fuerzaSuper = superheroeDeseado.streghtForceLevel
                val solteroSup = superheroeDeseado.single
                et_nombreSuperheroeAct.setText(nombreSuper)

                if(solteroSup.equals(true)){
                    rb_friendzoneAct2.isChecked = true
                }else{
                    rb_amarradoAct2.isChecked = true
                }
                et_nuevaFuerza.setText(fuerzaSuper.toString())

                btn_actSuperheroe.setOnClickListener{
                    // FUERZA
                    val nuevaFuerza = et_nuevaFuerza.text.toString().toDouble()
                    if(nuevaFuerza == fuerzaSuper){
                        Snackbar.make(it, "SIGUE SIENDO IGUAL DE FUERTE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }else{
                        superheroeDeseado.streghtForceLevel = nuevaFuerza
                        Snackbar.make(it, "FUERZA ACTUALIZADA", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                        et_nuevaFuerza.setText("")
                        adaptador.notifyDataSetChanged()
                    }

                    val estado = rbgr_estado.getCheckedRadioButtonId()
                    Log.i("Estado","Es soltero: ${estado}")
                    //ESTADO CIVIL
                    if(rb_amarradoAct2.isChecked){
                        superheroeDeseado.single = false
                        Snackbar.make(it, "SE ENAMORO!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    } else if (rb_friendzoneAct2.isChecked){
                        superheroeDeseado.single = true
                        Snackbar.make(it, "QUE SAD!FRIENDZONE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }


            }


    }
}
