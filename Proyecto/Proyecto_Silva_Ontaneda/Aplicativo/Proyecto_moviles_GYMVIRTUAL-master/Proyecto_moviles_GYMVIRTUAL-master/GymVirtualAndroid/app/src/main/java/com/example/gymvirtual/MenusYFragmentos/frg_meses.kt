package com.example.gymvirtual.MenusYFragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymvirtual.Adaptadores.RecyclerAdaptador
import com.example.gymvirtual.Interfaces.OnCalendario
import com.example.gymvirtual.R
//import com.example.gymvirtual.Retos

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class frg_meses : Fragment(), OnCalendario {
    var titulos: ArrayList<String> = arrayListOf()
    var imagenesURL: ArrayList<String> = arrayListOf()

    //var listaRetos= arrayListOf<Retos>()
    companion object{
        private const val ARG_OBJECT="object"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var vista:View=inflater.inflate(R.layout.fragment_frg_meses, container, false)
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageBitmaps()
        initRecyclerView()
    }

    fun initImageBitmaps(){
        imagenesURL.add("https://as1.ftcdn.net/jpg/03/68/21/80/500_F_368218082_73AQlyY8AT6aGCSDHSratVNDKHk3U7hR.jpg")
        titulos.add("Septiembre")
        imagenesURL.add("https://as1.ftcdn.net/jpg/03/68/95/90/500_F_368959039_ZBNLMrZW9NeGPo9KelrxF2CTz6PhxoQN.jpg")
        titulos.add("Octubre")
        imagenesURL.add("https://as2.ftcdn.net/jpg/03/69/26/01/500_F_369260134_r6GDUrjJTqY5z0xiH9n0iFqMlAL7dmSL.jpg")
        titulos.add("Noviembre")
        imagenesURL.add("https://as2.ftcdn.net/jpg/03/58/06/11/500_F_358061151_1zjaNHstZAAFca8OdQseyht9BfPwqB8i.jpg")
        titulos.add("Diciembre")
    }

    fun initRecyclerView(){
        var recyclerView: RecyclerView = view!!.findViewById(R.id.rv_meses)
        var adaptador = RecyclerAdaptador(requireActivity(),titulos,imagenesURL,this)
        recyclerView.setAdapter(adaptador)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireActivity())
        adaptador.notifyDataSetChanged()
    }

    override fun onCalendarioItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}