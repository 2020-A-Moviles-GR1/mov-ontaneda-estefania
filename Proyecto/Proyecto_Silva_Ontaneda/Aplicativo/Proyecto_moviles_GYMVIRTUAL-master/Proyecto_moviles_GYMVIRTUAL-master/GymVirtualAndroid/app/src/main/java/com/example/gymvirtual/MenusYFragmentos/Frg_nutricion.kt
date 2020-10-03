package com.example.gymvirtual.MenusYFragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymvirtual.Adaptadores.RecyclerAdaptador
import com.example.gymvirtual.Interfaces.OnCalendario
import com.example.gymvirtual.Menus.Act_MenuPrincipalNutricion
import com.example.gymvirtual.R
//import com.example.gymvirtual.Retos

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Frg_nutricion.newInstance] factory method to
 * create an instance of this fragment.
 */
class Frg_nutricion : Fragment(), OnCalendario {
    var titulos: ArrayList<String> = arrayListOf()
    var imagenesURL: ArrayList<String> = arrayListOf()

    //var listaRetos= arrayListOf<Retos>()
    companion object{
        private const val ARG_OBJECT="object"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var vista:View=inflater.inflate(R.layout.fragment_frg_nutricion, container, false)
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageBitmaps()
        initRecyclerView()
    }


    fun initImageBitmaps(){
        imagenesURL.add("https://cocina-casera.com/wp-content/uploads/2018/06/desayuno-saludable-2-770x485.jpg")
        titulos.add("Desayuno")
        imagenesURL.add("https://scontent.fuio4-1.fna.fbcdn.net/v/t1.0-9/49699929_2101772169881872_8787805371709259776_n.jpg?_nc_cat=111&_nc_sid=110474&_nc_eui2=AeHJHsC85cV4jw6opuF6mnXNz2pq5ur1gSLPamrm6vWBIsSz8tl0oAoPt6VIJjzXfWUM2MU84QuUSyWpaCR0mbU9&_nc_ohc=IGpJBpScmeUAX_A730I&_nc_ht=scontent.fuio4-1.fna&oh=54914687d3864821d73ec779f75e83d7&oe=5F8C2077")
        titulos.add("Almuerzo")
        imagenesURL.add("https://www.eluniversal.com.mx/sites/default/files/2018/10/17/como_armar_una_cena_saludable_menu_el_universal_istock_0.jpg")
        titulos.add("Cena")
        imagenesURL.add("https://cocina-casera.com/wp-content/uploads/2019/05/Los-8-postres-saludables-con-los-que-disfrutaras-mientras-te-cuidas-770x485.jpg")
        titulos.add("Postres")

    }

    fun initRecyclerView(){
        var recyclerView: RecyclerView = view!!.findViewById(R.id.rv_nutricion)
        var adaptador = RecyclerAdaptador(requireActivity(),titulos,imagenesURL,this)
        recyclerView.setAdapter(adaptador)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireActivity())
        adaptador.notifyDataSetChanged()
    }

    override fun onCalendarioItemClick(position: Int) {
        val intent= Intent(this.context, Act_MenuPrincipalNutricion::class.java)
        startActivity(intent)
    }
}