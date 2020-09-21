package com.example.funhero2.Maps

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.beust.klaxon.Klaxon
import com.example.funhero2.Modelo.SuperheroeHttp
import com.example.funhero2.Modelo.SuperheroeMapa
import com.example.funhero2.R
import com.example.funhero2.ServicioBDDMemoria
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.koushikdutta.ion.Ion
import java.util.concurrent.ExecutionException


class Act_MapaAllSuperheroes : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val urlPrincipal = "http://192.168.1.4:1337"
    var tienePermisos = false
    var urlString: ArrayList<String> = arrayListOf()
    var superheroes: ArrayList<SuperheroeHttp> = arrayListOf()
    var listaImagenes = arrayListOf<String>()
    var imagenURL:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act__mapa_all_superheroes)
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
       /* var arraySuperheroes = obtenerLatLngSuperheroes2()

        arraySuperheroes.forEach {
            var latitud = it.latitud.toString().toDouble()
            var longitud =  it.longitud.toString().toDouble()
            imagenURL = it.imagenURL.toString()
            var latLng = LatLng(latitud,longitud)
            var nombre = it.nameSuperheroe.toString()
            anadirMarcador(latLng,imagenURL,nombre)
            val uri: Uri = Uri.parse(it.imagenURL)
            mMap.setOnMarkerClickListener {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
        }
    }*/

    marcadorConURL()
    }

    /*fun obtenerLatLngSuperheroes():ArrayList<LatLng>{
        val url = urlPrincipal + "/superheroe"
        var datosSuperheroes:ArrayList<LatLng>
        datosSuperheroes = arrayListOf()
        var peticion = url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val superheroes =
                            Klaxon().converter(ServicioBDDMemoria.convertidorUsuario())
                                .parseArray<SuperheroeHttp>(data)
                        if (superheroes != null) {
                            superheroes.forEach {
                                Log.i("Superheroe", "Json a Array latitud ${it.latitud.toString()} longitud: ${it.longitud.toString()}")
                                var latitud: Double = it.latitud?.toDouble() ?: 0.00
                                var longitud: Double = it.longitud?.toDouble() ?: 0.00
                                datosSuperheroes.add(LatLng(latitud, longitud))
                                urlString.add(it.imagenURL?: "")
                            }
                        }
                    }
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("Error", "ERROR: ${error}")
                    }
                }
            }
        peticion.join()
        return datosSuperheroes
    }*/

    fun obtenerLatLngSuperheroes2():ArrayList<SuperheroeMapa>{
        val url = urlPrincipal + "/superheroe"
        val superheroeMapa = arrayListOf<SuperheroeMapa>()
        //var datosSuperheroes:ArrayList<SuperheroeHttp>
        //datosSuperheroes = arrayListOf()
        var peticion = url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val superheroes =
                            Klaxon().converter(ServicioBDDMemoria.convertidorUsuario())
                                .parseArray<SuperheroeHttp>(data)
                        if (superheroes != null) {
                            superheroes.forEach {
                                Log.i("Superheroe", "Json a Array ${it.longitud}")
                                superheroeMapa.add(SuperheroeMapa(it.id,it.nameSuperheroe, it.latitud, it.longitud, it.imagenURL))
                            }
                        }
                    }
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("Error", "ERROR: ${error}")
                    }
                }
            }
        peticion.join()
        return superheroeMapa
    }

    fun anadirMarcador(latLng: LatLng, url: String, nombreSuperheroe:String) {
        val origen = LatLng(-0.232283, -78.513623)
      mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origen, 12F))
        try {
            val bmImg: Bitmap = Ion.with(this).load(url).asBitmap().get()
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory
                        .fromBitmap(bmImg))
                    .title(nombreSuperheroe)
            )
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }

    fun solicitarPermisos() {
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            Log.i("MAPA", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun marcadoresVillanos() {

        val origen = LatLng(-0.232283, -78.513623)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origen, 12F))
        var datos: ArrayList<LatLng>
        datos = arrayListOf()
        datos.add(LatLng(-0.3527114000000001, -78.5414629))
        datos.add(LatLng(-0.3533981, -78.5476427))
        datos.add(LatLng(-0.3533981, -78.5476427))
        datos.add(LatLng(-0.3533981, -78.5476427))
        datos.add(LatLng(-0.3389788, -78.5452394))
        datos.add(LatLng(-0.348935, -78.5579424))
        datos.forEach {
            mMap.addMarker(
                MarkerOptions().
                position(it)
                    .icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.batman)
                    )
                    .title("Villano"))

        }
    }

    fun marcadorConURL(){
        var latLng =LatLng(-0.232283, -78.513623)
        try {
            val bmImg: Bitmap = Ion.with(this).load("https://icon-icons.com/icons2/1412/PNG/96/comics-batman-joker_97410.png").asBitmap().get()
            mMap.addMarker(
                MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(bmImg))
            )
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }


}