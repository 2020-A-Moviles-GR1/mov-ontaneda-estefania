package Modelo

class IngredienteHttp(
    var id: Int,
    var nombre_ingred: String?,
    var receta: Any?
){
    override fun toString(): String {
        return "${nombre_ingred} \n"
    }
}