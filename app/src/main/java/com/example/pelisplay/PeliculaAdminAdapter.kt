package com.example.pelisplay

import Pelicula
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdminAdapter(private val peliculas: List<Pelicula>) :
    RecyclerView.Adapter<PeliculaAdminAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPeli: ImageView = itemView.findViewById(R.id.imgPelicula)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTituloPeli)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula_admin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val peli = peliculas[position]
        holder.imgPeli.setImageResource(peli.idDrawable)
        holder.txtTitulo.text = peli.titulo

        // Animación: entrada desde la derecha con fade
        holder.itemView.apply {
            alpha = 0f
            translationX = 80f
            animate()
                .alpha(1f)
                .translationX(0f)
                .setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun getItemCount(): Int = peliculas.size
}
