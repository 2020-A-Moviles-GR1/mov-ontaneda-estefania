package com.example.gymvirtual.MenusYFragmentos

import Modelo.ServicioBDDMemoria
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.example.gymvirtual.Adaptadores.Adaptador_progreso
import com.example.gymvirtual.Interfaces.OnCalendario
import com.example.gymvirtual.Modelo.EjercicioHttp
import com.example.gymvirtual.Modelo.RutinaHttp
import com.example.gymvirtual.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Frg_progresoEjer.newInstance] factory method to
 * create an instance of this fragment.
 */
class Frg_progresoEjer : Fragment(), OnCalendario {
    var titulos: ArrayList<String> = arrayListOf()
    var imagenesURL: ArrayList<String> = arrayListOf()
    var fecha: ArrayList<String> = arrayListOf()
    val urlPrincipal = "http://192.168.1.4:1337"

    companion object{
        private const val ARG_OBJECT="object"
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initImageBitmaps()
        initRecyclerView()
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageBitmaps()
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frg_progreso_ejer, container, false)
    }

    fun obtenerRutina():ArrayList<EjercicioHttp>{
        val url = urlPrincipal + "/Rutina"
        val ejercicioHttpArray = arrayListOf<EjercicioHttp>()
        var peticion = url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.get()
                        val rutinas = Klaxon().converter(ServicioBDDMemoria.convertidorRutina()).parseArray<RutinaHttp>(data)
                        if (rutinas != null) {
                            rutinas.forEach {
                                it.ejercicios!!.forEach {
                                    ejercicioHttpArray.add(
                                        EjercicioHttp(it.id, it.nombre_ejercicio,
                                        it.url_ejercicio,it.url_Imagen,it.tiempo,it.rutina)
                                    )
                                }
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
        return ejercicioHttpArray
    }
    fun initImageBitmaps(){
       imagenesURL.add("data:image/jpeg;base64,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")
        titulos.add("Abdominales")
       fecha.add("2020-10-01")
    }

    fun initRecyclerView(){
        var recyclerView: RecyclerView = view!!.findViewById(R.id.rv_progreso)
        var adaptador = Adaptador_progreso(requireActivity(),titulos,imagenesURL,fecha,this)
        recyclerView.setAdapter(adaptador)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireActivity())
    }
    override fun onCalendarioItemClick(position: Int) {
        /*val intent= Intent(this.context, Act_Progreso::class.java)
        startActivity(intent)*/
    }

}