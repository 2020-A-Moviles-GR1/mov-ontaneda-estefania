package com.example.funhero2.Modelo

import android.os.Parcel
import android.os.Parcelable

class SuperheroeHttp(
    var id:Int,
    var nameSuperheroe: String?,
    var single: String?,
    var streghtForceLevel: String?,
    var age: String?,
    var comicName: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "El nombre del superhéroe es: ${nameSuperheroe} \n Pertenece a: ${comicName}" +
                "\n Es soltero?: ${single} \n Su nivel de fuerza es: ${streghtForceLevel}\n " +
                "Su edad es: ${age}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameSuperheroe)
        parcel.writeString(single)
        parcel.writeString(streghtForceLevel)
        parcel.writeString(age)
        parcel.writeString(comicName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SuperheroeHttp> {
        override fun createFromParcel(parcel: Parcel): SuperheroeHttp {
            return SuperheroeHttp(parcel)
        }

        override fun newArray(size: Int): Array<SuperheroeHttp?> {
            return arrayOfNulls(size)
        }
    }
}




