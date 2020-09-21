package com.example.funhero2

import android.util.Log
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.ComicMod
import com.example.funhero2.Modelo.SuperheroeHttp
import com.example.funhero2.Modelo.SuperheroeMapa
import com.example.funhero2.Modelo.SuperheroeMod

class ServicioBDDMemoria{


    companion object{
        var nameSuperheroe: String = ""
        var single: String = "true"
        var streghtForceLevel: String = ""
        var age: String = ""
        var comicName: String =""
            //listaSuperheroe.add(SuperheroeMod(nameSuperheroe,single,streghtForceLevel,age,comicName))

        fun convertidorUsuario(): Converter {
            val miConvertidorUsuarioHttp = object : Converter {
                override fun canConvert(cls: Class<*>) = cls == SuperheroeHttp::class.java

                override fun toJson(value: Any): String =
                    """{"flag" : "${if ((value as SuperheroeHttp).comicName != null) 1 else 0}"""

                override fun fromJson(jv: JsonValue): SuperheroeHttp {
                    return SuperheroeHttp(
                        jv.objInt("id"),
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objString("nameSuperheroe"),
                        jv.objString("single"),
                        jv.objString("streghtForceLevel"),
                        jv.objString("age"),
                        jv.objString("comicName"),
                        jv.objString("latitud"),
                        jv.objString("longitud"),
                        jv.objString("imagenURL")
                    )
                }
            }
            return miConvertidorUsuarioHttp
        }

        fun convertidorSuperheroe(): Converter {
            val miConvertidorSuperheroe = object : Converter {
                override fun canConvert(cls: Class<*>) = cls == SuperheroeMapa::class.java

                override fun toJson(value: Any): String =
                    """{"flag" : "${if ((value as SuperheroeMapa).imagenURL != null) 1 else 0}"""

                override fun fromJson(jv: JsonValue): SuperheroeMapa {
                    return SuperheroeMapa(
                        jv.objInt("id"),
                        jv.objString("nameSuperheroe"),
                        jv.objString("latitud"),
                        jv.objString("longitud"),
                        jv.objString("imagenURL")
                    )
                }
            }
            return miConvertidorSuperheroe
        }
        }



    }
        /*var listaComic = arrayListOf<ComicMod>()

        fun añadirComic(
             nombreComic: String,
             vigenciaComic : Boolean,
             paginasComic : Int,
             precioComic :Float
        ){
            //listaComic.add(ComicMod(nombreComic,vigenciaComic,paginasComic,precioComic))
            Log.i("Lista", "La lista es: "+listaComic.toString())
        }



    }*/