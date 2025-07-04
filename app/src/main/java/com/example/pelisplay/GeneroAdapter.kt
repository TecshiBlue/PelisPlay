package com.example.pelisplay

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GeneroAdapter(private var generos: List<GeneroConPeliculas>) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    // ViewHolder para un género que contiene un título y un RecyclerView interno
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

        // Configura el RecyclerView horizontal para las películas
        holder.recyclerPeliculas.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        // Adaptador interno para la lista de películas
        holder.recyclerPeliculas.adapter = PeliculaAdapter(genero.peliculas) { pelicula ->
            val intent = Intent(holder.itemView.context, DetallePeliculaActivity::class.java)
            intent.putExtra("titulo", pelicula.titulo)
            intent.putExtra("imagen", pelicula.imagen)
            intent.putExtra("descripcion", pelicula.descripcion)
            holder.itemView.context.startActivity(intent)
        }


        // Animación de entrada para cada género
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

    // Permite actualizar la lista desde afuera, por ejemplo al aplicar un filtro
    fun actualizarLista(nuevaLista: List<GeneroConPeliculas>) {
        generos = nuevaLista
        notifyDataSetChanged()
    }
}
