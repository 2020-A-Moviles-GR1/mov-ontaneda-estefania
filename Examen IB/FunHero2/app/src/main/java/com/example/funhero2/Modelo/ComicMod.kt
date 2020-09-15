package com.example.funhero2.Modelo

class ComicMod(
    var nombreComic: String,
    var vigencia: String,
    var paginas: String,
    var precio: String) {

    override fun toString(): String {
        return "El nombre del comic es: ${nombreComic} \n La vigencia es: ${vigencia}" +
                "\n El número de pags es: ${paginas} \n El precio de es: ${precio}\t"
    }
}