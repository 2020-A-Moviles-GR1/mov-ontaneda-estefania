package com.example.nicou

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //Hay que verificar los permisos antes

        establecerConfiguracionMapa(mMap)
        //Establecer marcadores
        val casa = LatLng(-0.347685, -78.427692)
        //Zoom de la camara
        val zoom = 17f
        //Titulo
        val titulo = "Cerca de casa"
        val puntoUsuario = LatLng(-0.348870, -78.427354)
        anadirMarcador(casa,titulo)
        moverCamaraZoom(puntoUsuario,zoom)

        val sydney = LatLng(-34.0, 151.0)
        /*mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    //Añadir marcador
    fun anadirMarcador(latLng: LatLng, title:String){
        mMap.addMarker(MarkerOptions().
            position(latLng)
            .title(title)
        )
    }

    fun moverCamaraZoom(latLng: LatLng, zoom: Float = 10f){
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng,zoom)
        )
    }

    fun establecerConfiguracionMapa(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            //Revisar permisos
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            //wl this es del googlemaps
            //Habilitacion del zoom
            //this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        //vemos si el usuario YA dio los permisos
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos){
            Log.i("MAPA", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,//Contexto
                arrayOf(//Arreglo de permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //Codigo que esperamos
            )
        }
    }
}