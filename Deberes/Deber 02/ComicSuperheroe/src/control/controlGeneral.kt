package control

import clases.Comic
import java.io.File
import javax.swing.JOptionPane

class controlGeneral(){
    var comicPrincipal = comicControl()
    var superheroePrincipal = superheroeControl()
    var ficheroComic = File("archivos\\comics.txt")
    var ficheroSuperheroe = File("archivos\\superheroes.txt")

    fun comprobarBComic(archivo: File) : Boolean{
        try {
            if (!archivo.exists()) {
                archivo.createNewFile()
                return true
            } else {
                println("El archivo ya existe")
                return false
            }
        } catch (ex: Exception) {
            println(ex.message)
        }
        return false
    }


    fun menuPrincipal(){
        if (comprobarBComic(ficheroComic)){
            println("El archivo para comic fue creado exitosamente")
        }
        if (comprobarBComic(ficheroSuperheroe)){
            println("El archivo para superheroes fue creado exitosamente")
        }
        println("*********************     WELCOME     ************************\n" +
                "*********************     COMICS      ************************\n" +
                "*********************        &        ************************\n" +
                "*********************   SUPERHEROES   ************************\n")
        println("                     A donde deseas ir?                       \n" +
                "                     1.   COMIC                               \n" +
                "                     2.  SUPERHEROE                           \n" +
                "                     3.   SALIR                               \n")

        val opcion = readLine()!!.toInt()
        when(opcion){
            1 -> while (comicPrincipal.comicMenuOptions()){}
            2 -> while(superheroePrincipal.superheroeMenuOptions()){}
            3 -> System.exit(0)
        }
    }

    fun menuPrin(){
        val comic1 = comicControl()
        val super1 = superheroeControl()

        if (comprobarBComic(ficheroComic)){
            println("El archivo para comic fue creado exitosamente")
        }
        if (comprobarBComic(ficheroSuperheroe)){
            println("El archivo para superheroes fue creado exitosamente")
        }
        val options = arrayOf("Comics", "Superheroes")
        val seleccion= JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menu principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0])
        println(seleccion)
        if(seleccion == 0){
            val options = arrayOf("Crear", "Buscar","Modificar","Eliminar")
            val select1=JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menu Comics", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0])
            if(select1 == 0){
                val nom = JOptionPane.showInputDialog(null, "¿Cuál es el nombre del cómic?")
                val vig = JOptionPane.showInputDialog(null, "¿Está vigente?")
                val pags = JOptionPane.showInputDialog(null, "¿Cuántas paginas tiene?")
                val precio = JOptionPane.showInputDialog(null, "¿Cuánto costo?")
                val nomCom = nom.toString()
                val vigCom = vig.toBoolean()
                val pagsCom = pags.toInt()
                val precioCom = precio.toDouble()
                if(comic1.insertarComic(nomCom,vigCom,pagsCom,precioCom)){
                    JOptionPane.showMessageDialog(null, "Comic creado exitosamente");
                    menuPrin()
                }else{
                    JOptionPane.showMessageDialog(null, "El cómic no pudo ser creado", "Error!!", JOptionPane.ERROR_MESSAGE);
                    menuPrin()
                }
            }else{
                if(select1 == 1){
                    val respuestabus = JOptionPane.showInputDialog(null, "Qué comic busca?")
                    val comicEncon : ArrayList<Comic> = comic1.buscarComic(respuestabus)
                    JOptionPane.showMessageDialog(null, "COMIC ENCONTRADO!!!")
                    //JOptionPane.showMessageDialog(null, comicEncon)
                }else{
                    if(select1 == 2){

                    }
                }
            }
        }
    }

}