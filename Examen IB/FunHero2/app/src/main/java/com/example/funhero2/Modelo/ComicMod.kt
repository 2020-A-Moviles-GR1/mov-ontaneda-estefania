package com.example.funhero2.Modelo

class ComicMod(
    var nombreComic: String,
    var vigencia: Boolean,
    var pags: Int,
    var precio: Double) {

    override fun toString(): String {
        return "El nombre del comic es: ${nombreComic} \n La vigencia es: ${vigencia}" +
                "\n El número de pags es: ${pags} \n El precio de es: ${precio}\t"
    }
}