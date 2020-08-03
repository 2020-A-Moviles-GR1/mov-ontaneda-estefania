package com.example.funhero2.principalesUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funhero2.ComicGeneral.Comic
import com.example.funhero2.R
import com.example.funhero2.SuperheroeGeneral.menuPrincipalSuperheroe
import kotlinx.android.synthetic.main.activity_comic_superhero_choose.*

class comicSuperheroChoose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_superhero_choose)
        iv_comic.setOnClickListener{
            irComic()
        }

        iv_superhero.setOnClickListener{
            irSuperheroe()
        }



}

    fun irComic (){
        val intentExplicito = Intent(
            this,
            Comic::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irSuperheroe(){
        val intentExplicito = Intent(
            this,
            menuPrincipalSuperheroe::class.java
        )
        this.startActivity(intentExplicito)
    }
}
