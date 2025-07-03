package com.example.pelisplay

import Pelicula
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(
    private val pelis: List<Pelicula>,
    private val showFavoriteButton: Boolean = true
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    inner class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imgPoster)
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val btnFavorito: ImageButton = itemView.findViewById(R.id.btnFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula_grid, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val peli = pelis[position]
        holder.imagen.setImageResource(peli.idDrawable)
        holder.titulo.text = peli.titulo

        val prefs = holder.itemView.context.getSharedPreferences("datosUsuario", Context.MODE_PRIVATE)
        val favoritos = Pelicula.fromJsonList(prefs.getString("favoritos", "[]") ?: "[]")
        val yaEsFavorito = favoritos.any { it.titulo == peli.titulo }

        // Mostrar icono actual según favorito
        holder.btnFavorito.setImageResource(
            if (yaEsFavorito) R.drawable.ic_favorite_check else R.drawable.ic_favorite_border
        )

        holder.btnFavorito.visibility = if (showFavoriteButton) View.VISIBLE else View.GONE

        holder.btnFavorito.setOnClickListener {
            val nuevosFavoritos = Pelicula.fromJsonList(prefs.getString("favoritos", "[]") ?: "[]").toMutableList()

            if (nuevosFavoritos.any { it.titulo == peli.titulo }) {
                nuevosFavoritos.removeAll { it.titulo == peli.titulo }
                Toast.makeText(holder.itemView.context, "Quitado de favoritos 💔", Toast.LENGTH_SHORT).show()
            } else {
                nuevosFavoritos.add(peli)
                Toast.makeText(holder.itemView.context, "Agregado a favoritos 💕", Toast.LENGTH_SHORT).show()
            }

            prefs.edit().putString("favoritos", Pelicula.toJsonList(nuevosFavoritos)).apply()
            notifyItemChanged(position)
        }

        // ✨ Animación
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

    private fun guardarEnFavoritos(context: Context, peli: Pelicula) {
        val prefs = context.getSharedPreferences("datosUsuario", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val listaJson = prefs.getString("favoritos", "[]")
        val listaActual = Pelicula.fromJsonList(listaJson ?: "[]").toMutableList()

        if (listaActual.none { it.titulo == peli.titulo }) {
            listaActual.add(peli)
            editor.putString("favoritos", Pelicula.toJsonList(listaActual))
            editor.apply()
        }
    }
}
