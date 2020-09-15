package com.example.nicou

import com.beust.klaxon.*

class Conversores {
    companion object {
        fun convertidorPokemon():Converter{
            val miConvertidorPokemonHttp = object : Converter {
                override fun canConvert(cls: Class<*>) = cls == PokemonHttp::class.java

                override fun toJson(value: Any): String =
                    """{"flag" : "${if ((value as PokemonHttp).usuario == true) 1 else 0}"""

                override fun fromJson(jv: JsonValue): PokemonHttp {
                    return if (jv.obj?.get("usuario") is Int) {
                        //CREACION SIN PROBLEMA
                        PokemonHttp(
                            jv.objInt("createdAt").toLong(),
                            jv.objInt("updatedAt").toLong(),
                            jv.objInt("id"),
                            jv.objString("nombre"),
                            jv.objInt("usuario")
                        )
                    } else {
                        PokemonHttp(
                            jv.objInt("createdAt").toLong(),
                            jv.objInt("updatedAt").toLong(),
                            jv.objInt("id"),
                            jv.objString("nombre"),
                            Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj?.get("usuario") as JsonObject)
                        )
                    }
                }
            }
            return miConvertidorPokemonHttp
        }

    fun convertidorUsuario():Converter{
        val miConvertidorUsuarioHttp = object : Converter {
            override fun canConvert(cls: Class<*>) = cls == UsuarioHttp::class.java

            override fun toJson(value: Any): String =
                """{"flag" : "${if ((value as UsuarioHttp).pokemons != null) 1 else 0}"""

            override fun fromJson(jv: JsonValue): UsuarioHttp {
                return UsuarioHttp(
                    jv.objInt("id"),
                    jv.objInt("createdAt").toLong(),
                    jv.objInt("updatedAt").toLong(),
                    jv.objString("cedula"),
                    jv.objString("nombre"),
                    jv.objString("correo"),
                    jv.objString("estadoCivil"),
                    jv.objString("password"),
                    Klaxon().parseFromJsonArray<PokemonHttp>(jv.obj?.get("pokemons") as JsonArray<*>) as ArrayList<PokemonHttp>
                )
            }
        }
        return miConvertidorUsuarioHttp
    }

        }
    }
