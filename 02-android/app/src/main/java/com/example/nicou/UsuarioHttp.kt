package com.example.nicou

import java.util.*

class UsuarioHttp(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var cedula: String,
    var nombre: String,
    var correo: String,
    var estadoCivil: String,
    var password: String,
    var pokemons: ArrayList<PokemonHttp>? = null,
    var batalla : Int? = null
    //Dentro de pokemon el usuario no trae a sus hijos pokemons
){
    var fechaCreacion: Date
    var fechaActualizacion: Date
    init{
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
    //without trash
    override fun toString(): String {
        return "NOMBRE USUARIO CONVERSOR ${nombre}, CON ID: ${id}"
    }
}