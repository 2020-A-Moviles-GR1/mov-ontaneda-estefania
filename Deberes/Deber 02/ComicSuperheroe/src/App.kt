import Vistas.MenuComics
import Vistas.MenuSuperheroes
import Vistas.Principal
import javax.swing.JFrame

var frameMenuComics:JFrame = JFrame()
var frameMenuSuperheroe:JFrame = JFrame()
var frameMenuPrincipal:JFrame = JFrame()

fun main(args: Array<String>): Unit {
    val frame = JFrame("Principal")
    frame.contentPane = Principal().principal
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}

fun abrirMenuPrincipal(){
    val frame = JFrame("Principal")
    frame.contentPane = Principal().principal
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
    frameMenuPrincipal=frame
}

fun abrirMenuComics(){
    val frame = JFrame("MenuComics")
    frame.contentPane = MenuComics().menuComics
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
    frameMenuComics=frame
}

fun abrirMenuSuperheroes(){
    val frame = JFrame("MenuSuperheroes")
    frame.contentPane = MenuSuperheroes().menuSuperh
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
    frameMenuSuperheroe = frame
}

fun cerrarMenuPrincipal(){
    frameMenuPrincipal.dispose()
}

fun cerrarMenuCómics(){
    frameMenuComics.dispose()
}

fun cerrarMenuSuperheroes(){
    frameMenuSuperheroe.dispose()
}