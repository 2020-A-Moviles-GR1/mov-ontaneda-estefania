import java.util.*
import java.util.Locale.filter
import java.util.function.Consumer
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    //Varibles Mutables e inmutables
    /*//println("Hola")
    //Int edadNico = 22;

    /*Mutable
    var edadNico = 22
    --- No necesito asignar un valor
    var nombreVariable: X --> TIPO DE DATO
    var edadGaby: Int
    edadGaby = 8*/

    //INMUTABLES
    //val numCed = 172559
    //numCed = 182726

    //TIPOS DE VARIABLES
    //String
    //val studentName: String = "Stephanie"
    //val lastNameStudent: Char = 'O'
    //Double
   // val sueldo = 1000.34
    //Date
    //val fecha = Date() // Java: new Date()

    //CONDICIONALES
    /*when (sueldo) {
        1000.34 -> println("Sueldo superior")
        -1000.34 -> println("Sueldo básico")
        else -> println("No se reconoce el sueldo")
    }*/

    //val sueldoMayorAlEstablecido = if (sueldo == 1000.34) true else false
    //val calculoEspecial = 10
    //EXPRESION ?X : Y
    /*println(calcularSueldo(13.00,2000.00))
    println(calcularSueldo(10.00,3000.00,23))
    println(calcularSueldo(calculoEspecial = 2,sueldo=800.00,tasa=16.00))
    println(calcularSueldo(sueldo=500.00))
    imprimirMensaje()*/
    */

    // ARREGLOS --- 15/06/2020
    val arregloConstante: Array<Int> = arrayOf(1, 2, 3)
    val arregloCumpleanos: ArrayList<Int> = arrayListOf(30, 31, 22, 20)
    /*println(arregloCumpleanos)
    arregloCumpleanos.add(24)
    println(arregloCumpleanos)
    arregloCumpleanos.remove(30)
    println(arregloCumpleanos)*/

    //OPERADORES --- 15/06/2020
    /*operadores de arreglos, se puede utilizar forEach se ejecuta dentro de las llaves la porción de código
    * Usada para iterar
    * Los operadores estan disponibles en todos los lenguajes
    * foreach necesita funcion con parámetro, no devuelve nada es Unit
    * */
    arregloCumpleanos
            .forEach {
                //println("Valor de la iteración"+ it)
            }

    arregloCumpleanos
            .forEach { valorIteracion: Int ->
                //println("Valor iteracion:" + valorIteracion)
            }

    arregloCumpleanos
            .forEach({ valorIteracion: Int ->
                //println("Valor iteracion:" + valorIteracion)
            }
            )

    //indice de la iteracion en ese momento //Sintaxis de más de una variable

    //Nos devuelve más de una variable se debe hacer
    arregloCumpleanos
            .forEachIndexed { index: Int, iteracion: Int ->
                // println("Valor de la iteracion" + iteracion)
            }

    val respuestaArregloForEach = arregloCumpleanos
            .forEachIndexed { index: Int, iteracion: Int ->
                //println("Valor de la iteración" + iteracion)
            }
    //println(respuestaArregloForEach)//no devuelve nada es -- > Unit

    //Quiero hacer negativo a un arreglo
    /*Map -> Muta el arreglo;
            1)Enviamos el nuevo valor de la iteracion
            2)Nos devuelve el NUEVO ARREGLO con los valores modificados*/
    val respuestaMap = arregloCumpleanos
            .map { iterador: Int ->
                iterador * -1
            }
    /*println(respuestaMap)
    println(arregloCumpleanos)*/

    val respuestaMap2: List<Int> = arregloCumpleanos
            .map { iterador: Int ->
                val nuevoValor: Int = iterador * -1
                val otroValor: Int = nuevoValor * 2
                return@map otroValor
            }
    //println(respuestaMap2)

    /*--------- FILTER --------- Valores que me devuelvan los mayores a 23
        1)Devolver una expresion (TRUE OR FALSE)
        2)Nuevo arreglo que cumpla esa expresion
    */
    val respuestaFilter: List<Int> = arregloCumpleanos
            .filter { iteracion: Int ->
                val mayor23: Boolean = iteracion > 23
                return@filter mayor23
            }

    arregloCumpleanos
            .filter { it > 23 }
    /* println("Arreglo con números mayores a 23" + respuestaFilter)
    println("Arreglo original de cumpleaños"+ arregloCumpleanos)*/

    //******************    Clase 18/06/2020 ********************
    /*ANY --> OR
    * ALL --> AND
    *  1) Devolver una expresión
    *   2) Nos devuelve un booleano
    *  */

    val respuestaAny = arregloCumpleanos
            .any { iterador: Int ->
                return@any iterador < 25
            }
    /*Va a ver si hay al menos un elemento que sea menor que 25 y deberia devolvernos
    Como si hay al menos 1 menor que 25 nos devuelve verdadero.*/
    println("Operador ANY: " + respuestaAny)

    val respuestaAll = arregloCumpleanos
            .all {
                iterador : Int ->
                return@all iterador > 65
            }
    println("Operador all: " + respuestaAll)

    /*Reduce
    *  1) Devuelve el acumulado
    *  2) En que valor empieza
    * Los reduce se pueden ocupar con diferentes tipos de datos
    * */
    val respuestaReduce = arregloCumpleanos
            //Acumulador 0
            .reduce{acumulador, iteracion ->
                return@reduce acumulador + iteracion
            }
    println("Operador reduce: "+respuestaReduce)

    val respuestaReduceRight = arregloCumpleanos
            //Acumulador 0
            .reduceRight{acumulador, iteracion ->
                return@reduceRight acumulador + iteracion
            }
    println("Operador reduceRight: "+respuestaReduceRight)
    /*arregloCumpleanos.reduceRight
    * arregloCumpleaños.foldRight
    * */

    //FOLD --> ACUMULADOR INICIADO EN UN VALOR DIFERENTE DE CERO
    val respuestaFold = arregloCumpleanos
            .fold(100,{acumulador,iteracion ->
                return@fold acumulador - iteracion
                    }
                )
    println("Operador fold: "+respuestaFold)

    val respuestaFoldRight = arregloCumpleanos
            .foldRight(100,{acumulador,iteracion ->
                return@foldRight acumulador - iteracion
            }
            )
    println("Operador foldRight: " + respuestaFoldRight)

    /*Reducir el daño en 20% y menor a 18 no nos hace daño*/
     val vidaActual = arregloCumpleanos
             .map {it * 0.8}
             .filter { it >18 }
             .fold(100.00,{acumulador,iteracion->acumulador-iteracion})
             .also { println("La vida actual que le queda es: " + it) }

}//Cerrado del main

fun calcularSueldo(
        tasa: Double = 12.00, //requerido y se asigna un valor por defecto
        sueldo: Double, // requerido
        calculoEspecial: Int?=null //Valores por defecto o que puede ser nulo
        )
        : Double {
            if(calculoEspecial != null ){
                return sueldo * tasa * calculoEspecial
            }else{
                return sueldo * tasa
            }
}

fun imprimirMensaje():Unit{ //Unit-->void
    println("Holisss.... :D")
}

 //             CLASES ABSTRACTAS --- 18/06/2020
//Java implementation
abstract class NumerosJava{
     protected val numeroUno: Int
     private val numeroDos: Int
     constructor(uno:Int,dos:Int){
         numeroUno=uno
         numeroDos=dos
     }
 }

//Kotlin implementation
abstract class Numeros(
        val numeroUno:Int,
        val numeroDos: Int
        )
{}
    //Para instanciar podemos usar val nuevosNumeros = Numeros(1,2)
class Suma(
            uno:Int,
            dos: Int
    ):Numeros(uno,dos){
        fun sumar():Int{
            return this.numeroUno + this.numeroDos
        }
    }