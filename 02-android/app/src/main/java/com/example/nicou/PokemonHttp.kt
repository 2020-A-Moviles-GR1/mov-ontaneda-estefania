package com.example.nicou

import com.beust.klaxon.*
import java.util.*
import kotlin.collections.ArrayList

class PokemonHttp(
    var createdAt: Long,
    var updatedAt: Long,
    var id: Int,
    var nombre: String,
    var usuario: Any? //Puede aceptar Int o JsonObject
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "NombrePokemon Conversor:${nombre}, Usuario Conversor: ${usuario}"
    }

}
