package com.example.pelisplay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pelisplay.R

data class Pelicula(val imagen: Int, val titulo: String)

class PeliculaAdapter(private val pelis: List<Pelicula>) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imgPoster)
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula_grid, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val peli = pelis[position]
        holder.imagen.setImageResource(peli.imagen)
        holder.titulo.text = peli.titulo

        // ✨ Animación fade-in con scale-up
        holder.itemView.apply {
            alpha = 0f
            scaleX = 0.9f
            scaleY = 0.9f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun getItemCount(): Int = pelis.size
}

