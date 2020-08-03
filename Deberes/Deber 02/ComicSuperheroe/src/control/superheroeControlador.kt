package control

import clases.Comic
import clases.Superheroe
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

class superheroeControl() {

    var arrSuperheroe: ArrayList<Superheroe> = ArrayList<Superheroe>()
    val superheroetxt = File("archivos\\superheroes.txt")
    var comicInsert = comicControl()

    fun createSuperhero() {
        println("************   \t\t\t    SUPERHEROE !!!!\t\t\t *****************")
        println("          Como se llama el SUPERHEROE??????         ")
        val nombreSuperh = readLine()!!.toString()
        println("           El SUPERHEROE es soltero?                ")
        val single = readLine()!!.toBoolean()
        println("           Que nivel de fuerza tiene?               ")
        val streght = readLine()!!.toDouble()
        println("           Cuántos años tiene?                      ")
        val ageS = readLine()!!.toInt()
        println("           A que cómic pertenece?                   ")
        val comic = readLine()!!.toString()
        insertarSuperheroe(nombreSuperh, single, streght, ageS, comic)
    }

    fun updateSuperheroe() {
        println("Ingrese el nombre del superheroe que desea actualizar la fuerza")
        val nameSuperheroe1 = readLine().toString()
        println("Ingrese la fuerza del superheroe" + nameSuperheroe1)
        val nuevaFuerza = readLine()!!.toDouble()
        modificarSuperheroe(nameSuperheroe1, nuevaFuerza)
    }

    fun deleteSuperheroe() {
        println("Ingrese el nombre del SUPERHEROE que desea eliminar")
        val nombreSuperh = readLine()!!.toString()
        eliminarSuperheroe(nombreSuperh)
    }

    fun txtAArray(archivo: File): ArrayList<Superheroe> {
        try {
            var linea: String? = ""
            val leerFichero = BufferedReader(FileReader(superheroetxt))
            while (leerFichero.readLine().also { linea = it } != null) {
                val mistokes = StringTokenizer(linea, "\t")
                val nomSuperheroe = mistokes.nextToken().trim { it <= ' ' }
                val soltero = mistokes.nextToken().trim { it <= ' ' }
                val fuerza = mistokes.nextToken().trim { it <= ' ' }
                val edad = mistokes.nextToken().trim { it <= ' ' }
                val comic = mistokes.nextToken().trim { it <= ' ' }

                val solteroOf = soltero.toBoolean()
                val fuerzaOf = fuerza.toDouble()
                val edadOf = edad.toInt()
                arrSuperheroe.add(Superheroe(nomSuperheroe, solteroOf, fuerzaOf, edadOf, comic))
            }
            leerFichero.close()
        } catch (ex: Exception) {
            println(ex.message)
        }
        return arrSuperheroe
    }

    fun comprobarComic(comicName: String): Boolean {
        val arrayComic: ArrayList<Comic> = comicInsert.txtAArray(comicInsert.comictxt)
        arrayComic.forEach {
            if (it.nombreComic == comicName) {
                return true
            }
        }
        return false
    }

    fun insertarSuperheroe(nombreSuperheroe: String, solteria: Boolean, fuerza: Double,
                           age: Int, comicName: String) {
        comprobarArray(arrSuperheroe.size)
        if (comprobarComic(comicName)) {
            try {
                val Fescribe = BufferedWriter(OutputStreamWriter(FileOutputStream(superheroetxt, true), "utf-8"))
                Fescribe.write(nombreSuperheroe + "\t" + solteria.toString() + "\t" + fuerza.toString() + "\t" + age.toString() + "\t" + comicName + "\r\n")
                println("El superheroe ha sido insertado")
                Fescribe.close()
                arrSuperheroe.add(Superheroe(nombreSuperheroe, solteria, fuerza, age, comicName))
            } catch (ex: Exception) {
                println(ex.message)
            }
        } else {
            println("No se puede insertar debido a que no existe dicho comic")
        }
    }

    fun searchSuperheroe(){
        println("Ingrese el nombre del superheroe del cual desea conocer la información")
        var comicName:String = readLine()!!.toString()
        buscarSheroe(comicName)
    }
    fun buscarSheroe(comicName: String): ArrayList<Superheroe> {
        comprobarArray(arrSuperheroe.size)
        var arrayEncontrado : ArrayList<Superheroe> = ArrayList<Superheroe>()
        arrSuperheroe.forEach {
            if (it.nameSuperheroe.equals(comicName)) {
                println("--------------\t\t\t" + it.nameSuperheroe + "\t\t\t-------------------\n" +
                        "El nombre del superheroe es:" + it.nameSuperheroe + "\t\t" +
                        "Es soltero?: " + it.single + "\t\t" +
                        "Nivel de fuerza: " + it.streghtForceLevel + "\t\t" +
                        "Su edad es: " + it.age + "\t\t" +
                        "Pertenece a:" + it.comicName)
            }else{
                //println("El cómic no existe")
            }
        }
        return arrayEncontrado
    }

    fun deArrayTxt(arrayComic: java.util.ArrayList<Superheroe>) {
        comprobarArray(arrSuperheroe.size)
        try {
            arrayComic.forEach {
                val Fescribe = BufferedWriter(OutputStreamWriter(FileOutputStream(superheroetxt, true), "utf-8"))
                Fescribe.write(it.nameSuperheroe + "\t" + it.single.toString() + "\t" +
                        it.streghtForceLevel.toString() + "\t" + it.age.toString() + "\t" +
                        it.comicName.toString() + "\r\n")
                Fescribe.close()
            }
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun leerSuperheroe() {
        //println(arrSuperheroe.size)
        //comprobarArray(0)
        arrSuperheroe.clear()
        txtAArray(superheroetxt)
        println(" ******************        SUPERHEROE         *******************")
        imprimirPretty(arrSuperheroe)
        println("                      FIN  :D :) :(                         ")
    }

    fun imprimirPretty(arrayComic: java.util.ArrayList<Superheroe>) {
        arrSuperheroe.forEach {
            println("--------------\t\t\t" + it.nameSuperheroe + "\t\t\t-------------------\n" +
                    "El nombre del superheroe es:" + it.nameSuperheroe + "\t\t" +
                    "Es soltero?: " + it.single + "\t\t" +
                    "Nivel de fuerza: " + it.streghtForceLevel + "\t\t" +
                    "Su edad es: " + it.age + "\t\t" +
                    "Pertenece a:" + it.comicName)
        }
    }

    fun comprobarArray(cont: Int? ): Boolean {
        if (arrSuperheroe.size == 0) {
            txtAArray(superheroetxt)
            return true
        }
        return false
    }

    fun modificarSuperheroe(nombreSuperheroe: String, fuerza: Double) {
        comprobarArray(arrSuperheroe.size)
        var arrayEncontrado : ArrayList<Superheroe> = ArrayList<Superheroe>()
        arrSuperheroe.forEach {
            if (it.nameSuperheroe.equals(nombreSuperheroe)) {
                it.streghtForceLevel = fuerza
                println("\n**********************" + "El nombre del superheroe es:" + it.nameSuperheroe + "\t\t" +
                        "Es soltero?: " + it.single + "\t\t" +
                        "Nivel de fuerza: " + it.streghtForceLevel + "\t\t" +
                        "Su edad es: " + it.age + "\t\t" +
                        "Pertenece a:" + it.comicName)
                val bw = BufferedWriter(FileWriter(superheroetxt))
                bw.write("")
                deArrayTxt(arrSuperheroe)
            }
        }
        /*comprobarArray()
        if (comprobarSuperheroe(nombreSuperheroe)) {
            arrSuperheroe.forEach {
                it.streghtForceLevel = fuerza
                println("\n**********************" + "El nombre del superheroe es:" + it.nameSuperheroe + "\t\t" +
                        "Es soltero?: " + it.single + "\t\t" +
                        "Nivel de fuerza: " + it.streghtForceLevel + "\t\t" +
                        "Su edad es: " + it.age + "\t\t" +
                        "Pertenece a:" + it.comicName)
                val bw = BufferedWriter(FileWriter(superheroetxt))
                bw.write("")
                deArrayTxt(arrSuperheroe)
            }
        } else {
            println("No existe dicho superheroe")
        }*/
    }

    fun comprobarSuperheroe(nombreSuperheroe: String): Boolean {
        arrSuperheroe.forEach {
            return nombreSuperheroe.equals(it.nameSuperheroe)
        }
        return false
    }

    fun eliminarSuperheroe(nombreSuperheroe: String) {
        try {
            comprobarArray(arrSuperheroe.size)
            arrSuperheroe.removeIf {
                it.nameSuperheroe == nombreSuperheroe
            }
            val bw = BufferedWriter(FileWriter(superheroetxt))
            bw.write("")
            deArrayTxt(arrSuperheroe)
        } catch (ex: Exception) {
        }
    }

    fun eliminarSuperheroeComic(nombreComic: String) {
        try {
            comprobarArray(arrSuperheroe.size)
            arrSuperheroe.removeIf {
                it.comicName == nombreComic
            }
            val bw = BufferedWriter(FileWriter(superheroetxt))
            bw.write("")
            deArrayTxt(arrSuperheroe)
           // txtAArray(superheroetxt)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun menuSuperheroe() {
        println("--------********    SUPERHEROE MENU  ********-------");
        println("                                                    ")
        println("1.          CREATE A NEW SUPERHEROE                 ")
        println("2.          READ ALL SUPERHEROES                    ");
        println("3.          UPDATE NAME SUPERHEROE                  ");
        println("4.          DELETE A SUPERHEROE                     ");
        println("5.          SEARCH A SUPERHEROE                     ");
        println("6.          RETURN TO MAIN MENU                     ")
        println("0.          EXIT                                    ");
    }

    fun superheroeMenuOptions(): Boolean {
        println("***********   BIENVENIDO A LOS SUPERHEROES...  *************")
        println(arrSuperheroe.size)
        var opcion: Int = 1
        menuSuperheroe()
        println("*****************Ingrese el número de la opción deseada************")
        opcion = readLine()?.toInt() as Int
        when (opcion) {
            1 -> createSuperhero()
            2 -> leerSuperheroe()
            3 -> updateSuperheroe()
            4 -> deleteSuperheroe()
            5 -> searchSuperheroe()
            6 -> return false
            0 -> System.exit(0)
        }
        return true
    }
}
