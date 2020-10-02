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
import com.google.android.gms.maps.model.*

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener
{

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

        establecerConfiguracionMapa(mMap)
        establecerListener(mMap)

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

        val poliLineaUno = googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(-0.348486, -78.428033),
                    LatLng(-0.348009, -78.428071),
                    LatLng(-0.347752, -78.427556),
                    LatLng(-0.348379, -78.427529)
                )
        )

        val poligonoUnoo = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                    LatLng(-0.349178, -78.428119),
                    LatLng(-0.348604, -78.428178),
                    LatLng(-0.348620, -78.427459),
                    LatLng(-0.349307, -78.427480)
                )
        )
        poligonoUnoo.fillColor = -0xc771c4





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

    fun establecerListener(map:GoogleMap){
        with(map){
            setOnCameraIdleListener (this@MapsActivity)
            setOnCameraMoveStartedListener (this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolylineClickListener (this@MapsActivity)
            setOnPolygonClickListener (this@MapsActivity)
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("mapa","Empezando a mover OnCreate")
    }

    override fun onCameraMove() {
        Log.i("mapa","Moviendo onCameraMove")
    }

    override fun onCameraIdle() {
        Log.i("mapa","Quieto OnCameraIdle")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("mapa","Polilinea ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa","Poligono ${p0.toString()}")
    }
}