package com.example.gymvirtual.Modelo

class RutinaHttp (
    var id: Int,
    var ejercicios: ArrayList<EjercicioHttp>? = null
){
    override fun toString(): String {
        return "El id de la rutina es: ${id} \n el primer ejercicio es: ${ejercicios}"
    }
}