package com.example.nicou

import android.os.Parcel
import android.os.Parcelable
import java.lang.reflect.Parameter

class Mascota(var nombre: String?,
                var duenio: Usuario?
    ):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Usuario::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeParcelable(duenio, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mascota> {
        override fun createFromParcel(parcel: Parcel): Mascota {
            return Mascota(parcel)
        }

        override fun newArray(size: Int): Array<Mascota?> {
            return arrayOfNulls(size)
        }
    }

}