package com.example.gymvirtual.Modelo

import android.os.Parcel
import android.os.Parcelable

class EjercicioHttp(
    var id: Int,
    var nombre_ejercicio: String?,
    var url_ejercicio: String?,
    var url_Imagen: String?,
    var tiempo: String?,
    var rutina: Int?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun toString(): String {
        return "El id del ejercicio es: ${id}, el nombre del ejercicio es: ${nombre_ejercicio} y el url es: ${url_ejercicio}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre_ejercicio)
        parcel.writeString(url_ejercicio)
        parcel.writeString(url_Imagen)
        parcel.writeString(tiempo)
        parcel.writeValue(rutina)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EjercicioHttp> {
        override fun createFromParcel(parcel: Parcel): EjercicioHttp {
            return EjercicioHttp(parcel)
        }

        override fun newArray(size: Int): Array<EjercicioHttp?> {
            return arrayOfNulls(size)
        }
    }
}




