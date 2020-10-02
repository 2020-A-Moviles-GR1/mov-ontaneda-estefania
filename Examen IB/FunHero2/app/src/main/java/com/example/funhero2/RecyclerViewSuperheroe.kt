package com.example.funhero2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funhero2.Modelo.SuperheroeHttp

class RecyclerViewSuperheroe(
    var contexto : Context,
    var superHeroe: ArrayList<SuperheroeHttp>
): RecyclerView.Adapter<RecyclerViewSuperheroe.MyViewHolder>() {
    var arrayImagenes = arrayListOf<String>()
    var arrayNombresComic = arrayListOf<String>()
    var arrayNombresSuperheroes = arrayListOf<String>()
    var arrayFuerza = arrayListOf<String>()
    var arrayEdad = arrayListOf<String>()
    var arrayLatitud =  arrayListOf<String>()
    var arrayLongitud = arrayListOf<String>()

    inner class MyViewHolder(view: View):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        var imagen: ImageView
        var tv_comicSuper: TextView
        var tv_fuerzasuperheroe: TextView
        var tv_edadSuperheroe: TextView
        var tv_latSuper: TextView
        var tv_longSuper: TextView
        var tv_nombreSuper:TextView
        var parentLayout: RelativeLayout = view.findViewById(R.id.id_relativeLayout)
        init{
            imagen = view.findViewById(R.id.iv_recySuperheroe)
            tv_comicSuper = view.findViewById(R.id.tv_comicSuper)
            tv_fuerzasuperheroe = view.findViewById(R.id.tv_fuerzasuperheroe)
            tv_edadSuperheroe = view.findViewById(R.id.tv_edadSuperheroe)
            tv_latSuper = view.findViewById(R.id.tv_latSuper)
            tv_longSuper = view.findViewById(R.id.tv_longSuper)
            tv_nombreSuper = view.findViewById(R.id.tv_nombreSuperheroe)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int):
            MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_superheroes,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return superHeroe.size
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int) {
            superHeroe.forEach {
                arrayImagenes.add(it.imagenURL.toString())
                arrayNombresSuperheroes.add(it.nameSuperheroe.toString())
                arrayNombresComic.add(it.comicName.toString())
                arrayEdad.add(it.age.toString())
                arrayFuerza.add(it.streghtForceLevel.toString())
                arrayLatitud.add(it.latitud.toString())
                arrayLongitud.add(it.longitud.toString())
            }
            Glide.with(contexto)
                .asBitmap()
                .load(arrayImagenes[position])
                .into(holder.imagen)
       holder.tv_comicSuper.setText(arrayNombresComic[position])
        holder.tv_nombreSuper.setText(arrayNombresSuperheroes[position])
        holder.tv_edadSuperheroe.setText(arrayEdad[position])
        holder.tv_fuerzasuperheroe.setText(arrayFuerza[position])
        holder.tv_latSuper.setText(arrayLatitud[position])
        holder.tv_longSuper.setText(arrayLongitud[position])
    }

}
