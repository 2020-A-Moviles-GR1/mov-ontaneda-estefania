package com.example.nicou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    var numeroLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val listaUsuarios = arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
            UsuarioHttp(
            1,
            123,
            123,
            "1725590838",
            "Nico",
            "n@gmail.com",
            "Soltero",
            "123",
            arrayListOf<PokemonHttp>())
        )

        listaUsuarios.add(
            UsuarioHttp(
                2,
                1234,
                1234,
                "1708102486",
                "Daniel",
                "dani@gmail.com",
                "Soltero",
                "1236",
                arrayListOf<PokemonHttp>())
        )

        listaUsuarios.add(
            UsuarioHttp(
                3,
                12345,
                12345,
                "1725590837",
                "Christian",
                "chris@gmail.com",
                "Soltero",
                "12345",
                arrayListOf<PokemonHttp>())
        )

        iniciarRecyclerView(listaUsuarios,this,rv_usuarios)

    }

    fun iniciarRecyclerView(
        lista:List<UsuarioHttp>,
        actividad:RecyclerViewActivity,
        recycler_view:androidx.recyclerview.widget.RecyclerView
    ){
        //Instanciamos un nuevo recycler
        val adaptadorUsuario = RecyclerAdaptador(
            lista,
            actividad,
            recycler_view
        )
        rv_usuarios.adapter = adaptadorUsuario
        //Se necesitan las siguientes implementaciones
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        //Si ocurre algun cambio
        adaptadorUsuario.notifyDataSetChanged()

    }

    fun anadirLikesEnActividad(numero: Int){
        this.numeroLikes = this.numeroLikes + numero
        tv_titulo_rv.text = numeroLikes.toString()
    }
}
