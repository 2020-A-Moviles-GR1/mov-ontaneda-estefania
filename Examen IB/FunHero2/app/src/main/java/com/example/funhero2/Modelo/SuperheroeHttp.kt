package com.example.funhero2.Modelo

import android.os.Parcel
import android.os.Parcelable

class SuperheroeHttp(
    var id:Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nameSuperheroe: String?,
    var single: String?,
    var streghtForceLevel: String?,
    var age: String?,
    var comicName: String?,
    var latitud: String?,
    var longitud: String?,
    var imagenURL:String?
){


    override fun toString(): String {
        return "El nombre del superhéroe es: ${nameSuperheroe} \n Pertenece a: ${comicName}" +
                "\n Es soltero?: ${single} \n Su nivel de fuerza es: ${streghtForceLevel}\n " +
                "Su edad es: ${age}"
    }
}

