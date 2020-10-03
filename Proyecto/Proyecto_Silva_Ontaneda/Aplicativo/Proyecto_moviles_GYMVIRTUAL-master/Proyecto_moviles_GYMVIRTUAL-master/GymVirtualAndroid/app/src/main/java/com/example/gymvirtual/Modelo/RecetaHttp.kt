package Modelo

import java.util.ArrayList

class RecetaHttp(
    var id:Int,
    var nombre_rec:String?,
    var imagen_rec: String?,
    var preparacion_rec: String?,
    var ingredientes: ArrayList<IngredienteHttp>? = null,
    ){
    override fun toString(): String {
        return "El id de Receta ${id}, nombre de la receta: ${nombre_rec}, imagen receta: ${imagen_rec}, " +
                "preparación: ${preparacion_rec}, ingredientes: ${ingredientes}"
    }
    }