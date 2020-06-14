import java.util.*

fun main(args:Array<String>) {
    println("Hola")
    //Int edadNico = 22;

    //Mutable
    //var edadNico = 22
    //DUCK TYPING
    // --- No necesito asignar un valor
    // var nombreVariable: X --> TIPO DE DATO
    //    var edadGaby: Int
    //  edadGaby = 8

    //INMUTABLES
    //val numCed = 172559
    //numCed = 182726

    //TIPOS DE VARIABLES
    //String
    //val studentName: String = "Stephanie"
    //val lastNameStudent: Char = 'O'
    //Double
    val sueldo = 1000.34
    //Date
    //val fecha = Date() // Java: new Date()

    //CONDICIONALES
    when (sueldo) {
        1000.34 -> println("Sueldo superior")
        -1000.34 -> println("Sueldo básico")
        else -> println("No se reconoce el sueldo")
    }

    //val sueldoMayorAlEstablecido = if (sueldo == 1000.34) true else false
    //val calculoEspecial = 10
    //EXPRESION ?X : Y
    println(calcularSueldo(13.00,2000.00))
    println(calcularSueldo(10.00,3000.00,23))
    println(calcularSueldo(calculoEspecial = 2,sueldo=800.00,tasa=16.00))
    println(calcularSueldo(sueldo=500.00))
    imprimirMensaje()
}

fun calcularSueldo(
        tasa: Double = 12.00, //requerido y se asigna un valor por defecto
        sueldo: Double, // requerido
        calculoEspecial: Int?=null //puede ser nulo
        )
        : Double {
            if(calculoEspecial != null){
                return sueldo * tasa * calculoEspecial
            }else{
                return sueldo * tasa
            }
}

fun imprimirMensaje():Unit{ //Unit-->void
    println("Holisss.... :D")
}