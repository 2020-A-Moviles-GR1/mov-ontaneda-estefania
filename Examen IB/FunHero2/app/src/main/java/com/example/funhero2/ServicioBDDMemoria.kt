package com.example.funhero2

import android.util.Log
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.Modelo.SuperheroeMod

class ServicioBDDMemoria{


    companion object{
        var listaComic = arrayListOf<ComicMod>()

        fun añadirComic(
             nombreComic: String,
             vigenciaComic : Boolean,
             paginasComic : Int,
             precioComic :Double
        ){
            listaComic.add(ComicMod(nombreComic,vigenciaComic,paginasComic,precioComic))
            Log.i("Lista", "La lista es: "+listaComic.toString())
        }

        var listaSuperheroe = arrayListOf<SuperheroeMod>()

        fun añadirSuperheroe(nameSuperheroe: String,
                              single: Boolean,
                              streghtForceLevel: Double,
                              age: Int,
                              comicName: String){
            listaSuperheroe.add(SuperheroeMod(nameSuperheroe,single,streghtForceLevel,age,comicName))
            Log.i("Lista", "La lista es: "+ listaSuperheroe.toString())
        }

    }
}