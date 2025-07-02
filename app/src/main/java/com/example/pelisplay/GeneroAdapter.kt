package com.example.pelisplay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GeneroAdapter(private var generos: List<GeneroConPeliculas>) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

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

        holder.tituloGenero.text = genero.nombreGenero

        holder.recyclerPeliculas.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        holder.recyclerPeliculas.adapter = PeliculaAdapter(genero.peliculas)

        // 🎬 Animación: fade + slide + scale
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
                .setStartDelay((position * 80).toLong()) // efecto cascada
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }

    override fun getItemCount(): Int = generos.size

    // 🔁 Método para actualizar la lista desde fuera (por filtro)
    fun actualizarLista(nuevaLista: List<GeneroConPeliculas>) {
        generos = nuevaLista
        notifyDataSetChanged()
    }
}
