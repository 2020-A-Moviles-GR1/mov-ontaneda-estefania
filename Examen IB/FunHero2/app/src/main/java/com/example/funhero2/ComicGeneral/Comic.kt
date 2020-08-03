package com.example.funhero2.ComicGeneral

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.funhero2.R

class Comic() : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            irInsertarComic()
        }

        val buscarBoton: FloatingActionButton = findViewById(R.id.buscarComic)
        buscarBoton.setOnClickListener {
            irBuscarComic()
        }

        val actBoton: FloatingActionButton = findViewById(R.id.actualizarComic)
        actBoton.setOnClickListener {
            irActualizarComic()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_searchComic
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun irInsertarComic(){
        val intentExplicito = Intent(
            this,
            InsertarComic::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irBuscarComic(){
        val intentExplicito = Intent(
            this,
            BuscarComicActivity::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun irActualizarComic(){
        val intentExplicito = Intent(
            this,
            actualizarComic::class.java
        )
        this.startActivity(intentExplicito)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.comic, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
