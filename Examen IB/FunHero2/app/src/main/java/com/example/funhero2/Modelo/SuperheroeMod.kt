package com.example.funhero2.Modelo

class SuperheroeMod(var nameSuperheroe: String,
                 var single: Boolean,
                 var streghtForceLevel: Double,
                 var age: Int,
                 var comicName: String) {
    override fun toString(): String {
        return "El nombre del superhéroe es: ${nameSuperheroe} \n Pertenece a: ${comicName}" +
                "\n Es soltero?: ${single} \n Su nivel de fuerza es: ${streghtForceLevel}\n " +
                "Su edad es: ${age}"
    }
}