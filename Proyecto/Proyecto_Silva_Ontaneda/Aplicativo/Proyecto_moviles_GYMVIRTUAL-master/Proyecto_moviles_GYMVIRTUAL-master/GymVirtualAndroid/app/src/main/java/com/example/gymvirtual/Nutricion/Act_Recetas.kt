package Activities_MenusOpciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gymvirtual.R

class Act_Recetas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__recetas)
        getIncomingIntent()
    }

    fun getIncomingIntent(){
        if(intent.hasExtra("imagenURL")){
            var imagenURL = intent.getStringExtra("imagenURL")
            setImage(imagenURL)
        }
        if (intent.hasExtra("ingredientes")){
            var ingredientesF = intent.getStringExtra("ingredientes")
            setIngredientes("Ingredientes: "+ingredientesF)
        }
        if (intent.hasExtra("preparacion")){
            var preparacionF = intent.getStringExtra("preparacion")
            setPreparacion(preparacionF)
        }
        if (intent.hasExtra("nombreReceta")){
            var titulosF = intent.getStringExtra("nombreReceta")
            setTitulos(titulosF)
        }
    }

    fun setImage(imagenURL: String){
            var imagen: ImageView = findViewById(R.id.imageView13)
        Glide.with(this)
            .asBitmap()
            .load(imagenURL)
            .into(imagen)
    }

    fun setIngredientes (ingredientesArray: String){
        var ingredientes: TextView = findViewById(R.id.textView7)
        ingredientes.setText(ingredientesArray)
    }

    fun setPreparacion (preparacionArray: String){
        var preparacionTv: TextView = findViewById(R.id.textView)
        preparacionTv.setText(preparacionArray)
    }

    fun setTitulos (titulosArray: String){
        var tv_titulos: TextView = findViewById(R.id.textView4)
        tv_titulos.setText(titulosArray)
    }
}