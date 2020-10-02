package com.example.funhero2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.SuperheroeHttp
import com.example.funhero2.Modelo.SuperheroeMod
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class Listar_Superheroes : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.4:1337"
    var arraySuperheroes = arrayListOf<SuperheroeHttp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar__superheroes)
        arraySuperheroes = obtenerSuperheroes()
        arraySuperheroes.forEach {
            Log.i("Superheroes", "Superheroes: ${it}")
        }
        initRecyclerView()
    }

    fun initRecyclerView(){
        var recyclerView: RecyclerView = findViewById(R.id.rv_superheroes)
        var adaptador = RecyclerViewSuperheroe(this,arraySuperheroes)
        recyclerView.setAdapter(adaptador)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun obtenerSuperheroes(): ArrayList<SuperheroeHttp>{
        val url = urlPrincipal + "/superheroe"
        var listaSuperheroe = arrayListOf<SuperheroeHttp>()
        var peticion = url.httpGet()
            .responseString {
                    request, response, result ->
            when (result){
                is Result.Success ->{
                    val data = result.get()
                    val superheroes = Klaxon().parseArray<SuperheroeHttp>(data)
                    if(superheroes != null){
                        superheroes.forEach{
                            listaSuperheroe.add(SuperheroeHttp(it.id,it.createdAt,it.updatedAt,it.nameSuperheroe,it.single,it.streghtForceLevel,it.age,it.comicName,it.latitud,it.longitud,it.imagenURL))
                        }
                    }
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("KLAXON", "ERROR FAILURE:${ex.message}")
                }
            }
        }
        peticion.join()
        return listaSuperheroe
    }
}
