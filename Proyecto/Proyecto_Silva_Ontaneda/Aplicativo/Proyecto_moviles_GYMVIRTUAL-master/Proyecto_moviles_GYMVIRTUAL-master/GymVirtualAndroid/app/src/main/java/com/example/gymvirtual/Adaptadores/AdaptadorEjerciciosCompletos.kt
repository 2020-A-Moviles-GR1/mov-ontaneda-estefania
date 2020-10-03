package com.example.gymvirtual.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gymvirtual.R

class AdaptadorEjerciciosCompletos (var contexto : Context,
                                    var imagenes : ArrayList<String>,
                                    var titulos : ArrayList<String>,
                                    var tiempos: ArrayList<String>)
    : RecyclerView.Adapter<AdaptadorEjerciciosCompletos.MyViewHolder>() {
    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var titulo: TextView
        var imagen: ImageView
        var tiempo: TextView
        var checkBox:CheckBox
        var parentLayout: RelativeLayout = view.findViewById(R.id.id_relativeLayout2)

        init {
            imagen = view.findViewById(R.id.id_abdomen2)
            titulo = view.findViewById(R.id.tv_tituloEjercicio2)
            tiempo = view.findViewById(R.id.tv_tiempoEjercicio2)
            checkBox = view.findViewById(R.id.checkBox)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_ejercicios_completado, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imagenes.size
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        Glide.with(contexto)
            .asBitmap()
            .load(imagenes[position])
            .into(holder.imagen)
        holder.titulo.setText(titulos[position])
        holder.tiempo.setText(tiempos[position])
        holder.checkBox.setChecked(true)
        holder.parentLayout.setOnClickListener {
        }
    }
}