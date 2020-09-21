package com.example.funhero2.Modelo

class SuperheroeMapa(
    var id:Int,
    var nameSuperheroe: String?,
    var latitud: String?,
    var longitud: String?,
    var imagenURL:String?
){


    override fun toString(): String {
        return "El nombre del superhéroe es: ${nameSuperheroe}"
    }
}

