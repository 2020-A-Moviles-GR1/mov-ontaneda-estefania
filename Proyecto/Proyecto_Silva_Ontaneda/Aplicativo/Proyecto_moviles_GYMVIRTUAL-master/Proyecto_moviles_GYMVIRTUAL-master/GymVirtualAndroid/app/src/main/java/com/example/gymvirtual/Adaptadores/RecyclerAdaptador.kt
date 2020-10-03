package com.example.gymvirtual.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gymvirtual.Interfaces.OnCalendario
import com.example.gymvirtual.R

class RecyclerAdaptador(
    var contexto : Context,
    var titulos : ArrayList<String>,
    var imagenes : ArrayList<String>,
    var onCalendario: OnCalendario
):RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {

    lateinit var lsitener:View.OnClickListener

    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var titulo: TextView
        var imagen:ImageView
        var parentLayout:RelativeLayout= view.findViewById(R.id.id_relativeLayout2)

        init {
            titulo = view.findViewById(R.id.titulo)
            imagen = view.findViewById(R.id.id_abdomen)
            view.setOnClickListener{
                val position:Int=adapterPosition
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_retos,parent,false)
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imagenes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(contexto)
            .asBitmap()
            .load(imagenes[position])
            .into(holder.imagen)
        holder.titulo.setText(titulos[position])
        holder.parentLayout.setOnClickListener {
            onCalendario.onCalendarioItemClick(position)
        }
    }
}