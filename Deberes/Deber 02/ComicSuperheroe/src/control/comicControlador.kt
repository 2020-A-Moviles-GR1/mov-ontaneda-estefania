package control

import clases.Comic
import java.io.*
import java.util.*
import javax.swing.JOptionPane
import kotlin.collections.ArrayList


class comicControl(){

    var arrComic : ArrayList<Comic> = ArrayList<Comic>()
    val comictxt = File("archivos\\comics.txt")

    fun createComic() {
        println("************ \t\t\t CÓMICS \t\t\t*****************")
        println("    Ingresa el nombre del CÓMIC      ")
        val nombreCom = readLine()!!.toString()
        println("   Ingresa la vigencia del CÓMIC      ")
        val vigencia = readLine()!!.toBoolean()
        println("    Ingresa el #páginas del CÓMIC     ")
        val pags = readLine()!!.toInt()
        println("    Ingresa el precio del CÓMIC      ")
        val precioCom = readLine()!!.toDouble()
        insertarComic(nombreCom,vigencia,pags,precioCom)
    }

    fun updateComic(){
        println("Ingrese el nombre del comic que desea actualizar el precio")
        val nameComic1 = readLine()!!.toString()
        println("Ingrese el nuevo precio del Cómic")
        val nuevoPrecio = readLine()!!.toDouble()
        modificarComic(nameComic1, nuevoPrecio)
    }

    fun deleteComic(){
        println("Ingrese el nombre del cómic que desea eliminar")
        val nombreCom = readLine()!!.toString()
        println("Esta seguro de eliminar el cómic"+nombreCom+"? \n Si lo elimina se eliminaran " +
                "los superheroes que luchan en el :( \n Ingrese 1 para seguir y 0 para cancelar la eliminación")
        val opc = readLine()!!.toInt()
        if (opc == 1){
            eliminarComic(nombreCom)
        }
    }

    fun txtAArray(comictxt:File): ArrayList<Comic>{
        try {
            var linea : String? = ""
            val leerFichero = BufferedReader(FileReader(comictxt))
            while (leerFichero.readLine().also { linea = it }!=null){
                val mistokes = StringTokenizer(linea, "\t")
                val nomComic = mistokes.nextToken().trim{it <= ' '}
                val vigComic = mistokes.nextToken().trim{it <= ' '}
                val pagsComic = mistokes.nextToken().trim{it <= ' '}
                val precioComic = mistokes.nextToken().trim{it <= ' '}
                val vigComiOf = vigComic.toBoolean()
                val pagsComicOf = pagsComic.toInt()
                val precioComicOf = precioComic.toDouble()
               arrComic.add(Comic(nomComic,vigComiOf,pagsComicOf,precioComicOf))
            }
            leerFichero.close()
        }catch (ex: Exception){
            println(ex.message)
        }
    return arrComic
    }

    fun insertarComic(nombre: String, vigencia: Boolean, pags: Int, precio: Double ):Boolean{
        comprobarArray()
        try {
            val Fescribe = BufferedWriter(OutputStreamWriter(FileOutputStream(comictxt, true), "utf-8"))
            Fescribe.write(nombre + "\t" + vigencia.toString() + "\t" + pags.toString() + "\t" + precio.toString() + "\r\n")
            println("El cómic ha sido insertado")
            Fescribe.close()
            arrComic.add(Comic(nombre,vigencia,pags,precio))
            return true
        }catch (ex: Exception){
            println(ex.message)
        }
        return false
    }

    fun deArrayTxt(arrayComic: java.util.ArrayList<Comic>){
        comprobarArray()
        try {
            arrayComic.forEach {
                val Fescribe = BufferedWriter(OutputStreamWriter(FileOutputStream(comictxt, true), "utf-8"))
                Fescribe.write(it.nombreComic + "\t" + it.vigencia.toString() + "\t" + it.pags.toString() + "\t" + it.precio.toString()+ "\r\n")
                Fescribe.close()
            }
        }catch (ex: Exception){
            println(ex.message)
        }
    }
    fun leerComics(){
        comprobarArray()
        //porsi
       // txtAArray(comictxt)
        println(" ******************        COMICS         *******************")
        imprimirPretty(arrComic)
        println("                      FIN  :D :) \t :(                         ")
    }

    fun imprimirPretty(arrayComic : java.util.ArrayList<Comic>){
        arrComic.forEach {
            println("--------------\t\t\t"+     it.nombreComic +    "\t\t\t-------------------\n" +
                    "El nombre del cómic es:" + it.nombreComic + "\t\t" +
                    "Vigencia: " + it.vigencia + "\t\t"+
                    "Número de páginas: "+ it.pags + "\t\t"+
                    "El precio es: "+ it.precio )
        }
    }

    fun comprobarArray(): Boolean{
        if (arrComic.size == 0){
            txtAArray(comictxt)
            return true
        }
        return false
    }

    fun searchComic(){
        println("Ingrese el nombre del cómic del cual desea conocer la información")
        var comicName:String = readLine()!!.toString()
        buscarComic(comicName)
    }
        fun buscarComic(comicName: String): ArrayList<Comic> {
            comprobarArray()
            var arrayEncontrado : ArrayList<Comic> = ArrayList<Comic>()
            arrComic.forEach {
                if (it.nombreComic.equals(comicName)) {
                    println("--------------\t\t\t"+     it.nombreComic +    "\t\t\t-------------------\n" +
                            "El nombre del cómic es:" + it.nombreComic + "\t\t" +
                            "Vigencia: " + it.vigencia + "\t\t"+
                            "Número de páginas: "+ it.pags + "\t\t"+
                            "El precio es: "+ it.precio )
                }else{
                    //println("El cómic no existe")
                }
            }
            return arrayEncontrado
        }


    fun modificarComic(nombreComic: String, precioNuevo: Double){
        comprobarArray()
        arrComic.forEach {
            if (it.nombreComic.equals(nombreComic)){
                it.precio = precioNuevo
                println("--------------\t\t\t"+     it.nombreComic +    "\t\t\t-------------------\n" +
                        "El nombre del cómic es:" + it.nombreComic + "\t\t" +
                        "Vigencia: " + it.vigencia + "\t\t"+
                        "Número de páginas: "+ it.pags + "\t\t"+
                        "El precio es: "+ it.precio )
                val bw = BufferedWriter(FileWriter(comictxt))
                bw.write("")
                deArrayTxt(arrComic)
            }
        }
        }

    fun eliminarComic(nombreComic:String){
        try {
            comprobarArray()
            arrComic.removeIf {it.nombreComic == nombreComic
            }
            val bw = BufferedWriter(FileWriter(comictxt))
            bw.write("")
            deArrayTxt(arrComic)
            var superheroe = superheroeControl()
            superheroe.eliminarSuperheroeComic(nombreComic)
        }catch (ex: Exception){

        }
    }
    fun menu() {
        println("--------********    COMIC MENU  ********-------");
        println("                                               ")
        println("1.          CREATE A NEW COMIC                 ")
        println("2.          READ ALL COMICS                    ");
        println("3.          UPDATE NAME COMIC                  ");
        println("4.          DELETE A COMIC                     ");
        println("5.          SEARCH A COMIC                     ")
        println("6.          RETURN TO MAIN MENU                ")
        println("0.          EXIT                               ");
    }

    fun comicMenuOptions():Boolean {
        println("***********   BIENVENIDO AMANTE DEL CÓMIC...   *************")
        var opcion: Int = 1
        menu()
        println("*****************Ingrese el número de la opción deseada************")
        opcion = readLine()!!.toInt() as Int
        when (opcion) {
            1 -> createComic()
            2 -> leerComics()
            3 -> updateComic()
            4 -> deleteComic()
            5 ->searchComic()
            6 -> return false
            0 -> System.exit(0)
        }
        return true
    }
}