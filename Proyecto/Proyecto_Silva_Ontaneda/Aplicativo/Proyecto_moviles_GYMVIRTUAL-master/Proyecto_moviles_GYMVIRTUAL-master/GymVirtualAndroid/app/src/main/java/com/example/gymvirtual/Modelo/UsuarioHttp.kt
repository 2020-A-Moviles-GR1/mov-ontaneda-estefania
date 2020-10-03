package Modelo

import android.os.Parcel
import android.os.Parcelable

class UsuarioHttp(
    var id:Int,
    var nombre_usu:String?,
    var edad_usu:String?,
    var peso_usu:String?,
    var altura_usu: String?,
    var correo_usu: String?,
    var sexo_usu: String?
) {

    override fun toString(): String {
        return "Usuario: ${id},${nombre_usu},${edad_usu},${peso_usu},${altura_usu},${correo_usu},${sexo_usu},"
    }
}