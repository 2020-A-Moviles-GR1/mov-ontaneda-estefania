package com.example.funhero2.Modelo

import android.os.Parcel
import android.os.Parcelable
import com.beust.klaxon.Json

class ComicHttp(@Json(ignored = false)
    var id:Int,
    var nombreComic: String?,
    var vigencia: String?,
    var paginas: String?,
    var precio: String?

    ):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        //parcel.readBoolean(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "El nombre del comic es: ${nombreComic} \n La vigencia es: ${vigencia}" +
                "\n El número de pags es: ${paginas} \n El precio de es: ${precio}\t"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombreComic)
        parcel.writeString(vigencia)
        parcel.writeString(paginas)
        parcel.writeString(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicHttp> {
        override fun createFromParcel(parcel: Parcel): ComicHttp {
            return ComicHttp(parcel)
        }

        override fun newArray(size: Int): Array<ComicHttp?> {
            return arrayOfNulls(size)
        }
    }
}




