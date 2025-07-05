package com.example.pelisplay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 📚 Adaptador para mostrar una lista de géneros, cada uno con su propio RecyclerView de películas
class GeneroAdapter(private var generos: List<GeneroConPeliculas>) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    // 🎥 ViewHolder que contiene el nombre del género y su lista de películas
    inner class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloGenero: TextView = itemView.findViewById(R.id.tituloGenero)
        val recyclerPeliculas: RecyclerView = itemView.findViewById(R.id.recyclerPeliculas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genero_con_peliculas, parent, false)
        return GeneroViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = generos[position]

        // Mostrar el nombre del género
        holder.tituloGenero.text = genero.nombreGenero

        // Configurar RecyclerView horizontal con las películas del género
        holder.recyclerPeliculas.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        // Crear adaptador de películas y asignarlo
        val adaptadorPeliculas = PeliculaAdapter(genero.peliculas, showFavoriteButton = false)
        holder.recyclerPeliculas.adapter = adaptadorPeliculas

        // Agregar animación al contenedor del género
        holder.itemView.apply {
            alpha = 0f
            translationY = 60f
            scaleX = 0.9f
            scaleY = 0.9f
            animate()
                .alpha(1f)
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(500)
                .setStartDelay((position * 80).toLong())
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }

    override fun getItemCount(): Int = generos.size

    // Método para actualizar la lista desde afuera (por ejemplo, al aplicar filtros)
    fun actualizarLista(nuevaLista: List<GeneroConPeliculas>) {
        generos = nuevaLista
        notifyDataSetChanged()
    }
}
