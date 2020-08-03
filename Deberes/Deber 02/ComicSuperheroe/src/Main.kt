import Vistas.Principal
import control.controlGeneral
import javax.swing.JFrame

fun main(args: Array<String>) {
    val comicSuperheroe = controlGeneral()
    while (true) {
        comicSuperheroe.menuPrincipal()
    }
    //comicSuperheroe.menuPrin()

}
/*fun main(args: Array<String>): Unit {
    val frame = JFrame("Principal")
    frame.contentPane = Principal().principal
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}*/