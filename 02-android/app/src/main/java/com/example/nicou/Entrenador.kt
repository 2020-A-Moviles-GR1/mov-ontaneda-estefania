package com.example.nicou

class Entrenador(
    var nombre: String,
    var apellido: String)
    {
    override fun toString(): String {
        return "${this.nombre}  ${this.apellido}"
        //Templates strings
    }
}