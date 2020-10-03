package Modelo

import com.beust.klaxon.*
import com.bumptech.glide.load.model.ByteArrayLoader
import com.example.gymvirtual.Modelo.EjercicioHttp
import com.example.gymvirtual.Modelo.RutinaHttp

class ServicioBDDMemoria (){
    companion object {
        fun convertidorIngrediente(): Converter {
            val miConvertidorIngredienteHttp = object : Converter {
                override fun canConvert(cls: Class<*>) = cls == IngredienteHttp::class.java

                override fun toJson(value: Any): String =
                    """{"flag" : "${if ((value as IngredienteHttp).receta == true) 1 else 0}"""

                override fun fromJson(jv: JsonValue): IngredienteHttp {
                    return if (jv.obj?.get("receta") is Int) {
                        IngredienteHttp(
                            jv.objInt("id"),
                            jv.objString("nombre_ingred"),
                            jv.objInt("receta")
                        )
                    } else {
                        IngredienteHttp(
                            jv.objInt("id"),
                            jv.objString("nombre_ingred"),
                            Klaxon().parseFromJsonObject<RecetaHttp>(jv.obj?.get("receta") as JsonObject)
                        )
                    }
                }
            }
            return miConvertidorIngredienteHttp
        }

        fun convertidorReceta(): Converter {
            val miConvertidorRecetaHttp = object : Converter {
                override fun canConvert(cls: Class<*>) = cls == RecetaHttp::class.java

                override fun toJson(value: Any): String =
                    """{"flag" : "${if ((value as RecetaHttp).ingredientes != null) 1 else 0}"""

                override fun fromJson(jv: JsonValue): RecetaHttp {
                    return RecetaHttp(
                        jv.objInt("id"),
                        jv.objString("nombre_rec"),
                        jv.objString("imagen_rec"),
                        jv.objString("preparacion_rec"),
                        Klaxon().parseFromJsonArray<IngredienteHttp>(jv.obj?.get("ingredientes") as JsonArray<*>) as ArrayList<IngredienteHttp>
                    )
                }
            }
            return miConvertidorRecetaHttp
        }

        fun convertidorRutina(): Converter {
            val miConvertidorRutinaHttp = object : Converter {
                override fun canConvert(cls: Class<*>) = cls == RutinaHttp::class.java

                override fun toJson(value: Any): String =
                    """{"flag" : "${if ((value as RutinaHttp).ejercicios != null) 1 else 0}"""

                override fun fromJson(jv: JsonValue): RutinaHttp {
                    return RutinaHttp(
                        jv.objInt("id"),
                        Klaxon().parseFromJsonArray<EjercicioHttp>(jv.obj?.get("ejercicios") as JsonArray<*>) as ArrayList<EjercicioHttp>
                    )
                }
            }
            return miConvertidorRutinaHttp
        }
    }
}