package com.example.pelisplay

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador para mostrar una lista horizontal o grid de películas
class PeliculaAdapter(
    private val pelis: List<Pelicula>,
    private val onClick: ((Pelicula) -> Unit)? = null // callback opcional
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    // ViewHolder representa cada tarjeta de película (una imagen + título)
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

        // 🎯 Comportamiento al hacer clic
        holder.itemView.setOnClickListener {
            if (onClick != null) {
                onClick.invoke(peli)
            } else {
                // Si no se pasó un onClick, abre el detalle por defecto
                val context = holder.itemView.context
                val intent = Intent(context, DetallePeliculaActivity::class.java)
                intent.putExtra("titulo", peli.titulo)
                intent.putExtra("descripcion", peli.descripcion)
                intent.putExtra("imagen", peli.imagen)
                context.startActivity(intent)
            }
        }

        // 🎬 Animación de aparición (fade + scale)
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
