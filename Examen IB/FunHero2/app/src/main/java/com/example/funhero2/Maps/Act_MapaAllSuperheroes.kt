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
        establecerConfiguracionMapa(mMap)
       var arraySuperheroes = obtenerLatLngSuperheroes2()
        arraySuperheroes.forEach {
            var latitud = it.latitud.toString().toDouble()
            var longitud =  it.longitud.toString().toDouble()
            imagenURL = it.imagenURL.toString()
            var latLng = LatLng(latitud,longitud)
            var nombre = it.nameSuperheroe.toString()
            anadirMarcador(latLng,imagenURL,nombre)
            val uri: Uri = Uri.parse(it.imagenURL)

            mMap.setOnMarkerClickListener {
                val intentExplicito = Intent(Intent.ACTION_VIEW, Uri.parse(it.snippet))
                startActivity(intentExplicito)
                true
        }
    }
        marcadorConURLVillanos()

    }

    fun obtenerLatLngSuperheroes2():ArrayList<SuperheroeMapa>{
        val url = urlPrincipal + "/superheroe"
        val superheroeMapa = arrayListOf<SuperheroeMapa>()
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
        try {
            val bmImg: Bitmap = Ion.with(this).load(url).asBitmap().get()
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory
                        .fromBitmap(bmImg))
                    .title(nombreSuperheroe)
                    .snippet(url)
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
    val origen = LatLng(-0.346810, -78.426605)
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origen, 15F))
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

    fun marcadorConURLVillanos() {
        val latLongJoker = LatLng(-0.348333, -78.428333)
        val latlongThanos = LatLng(-0.345586, -78.427196)
        val latlongHarley = LatLng(-0.346091, -78.429502)
            try {
                val bmImg: Bitmap = Ion.with(this)
                    .load("https://icon-icons.com/icons2/1412/PNG/96/comics-batman-joker_97410.png")
                    .asBitmap().get()
                mMap.addMarker(
                    MarkerOptions().position(latLongJoker).icon(BitmapDescriptorFactory.fromBitmap(bmImg))
                )

                val bmImg2: Bitmap = Ion.with(this)
                    .load("https://icon-icons.com/icons2/467/PNG/72/034_Thanos_2x_44249.png")
                    .asBitmap().get()
                mMap.addMarker(
                    MarkerOptions().position(latlongThanos).icon(BitmapDescriptorFactory.fromBitmap(bmImg2))
                )

                val bmImg3: Bitmap = Ion.with(this)
                    .load("https://icon-icons.com/icons2/1394/PNG/72/harley01_96780.png")
                    .asBitmap().get()
                mMap.addMarker(
                    MarkerOptions().position(latlongHarley).icon(BitmapDescriptorFactory.fromBitmap(bmImg3))
                )
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }
    }

}