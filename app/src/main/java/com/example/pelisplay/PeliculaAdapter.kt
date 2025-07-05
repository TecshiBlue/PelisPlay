package com.example.pelisplay

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter(
    private val pelis: List<Pelicula>,
    private val showFavoriteButton: Boolean = true
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    // ViewHolder que representa cada ítem de película
    inner class PeliculaViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val img = v.findViewById<ImageView>(R.id.imgPoster)
        val txt = v.findViewById<TextView>(R.id.txtTitulo)
        val btnFav = v.findViewById<ImageButton>(R.id.btnFavorito)
    }

    // Infla el layout de ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula_grid, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun getItemCount(): Int = pelis.size

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val peli = pelis[position]

        // Asigna imagen y texto
        holder.img.setImageResource(peli.idDrawable)
        holder.txt.text = peli.titulo

        // Obtiene la lista de favoritos desde SharedPreferences
        val prefs = holder.itemView.context.getSharedPreferences("datosUsuario", Context.MODE_PRIVATE)
        val favJson = prefs.getString("favoritos", "[]") ?: "[]"
        val favoritos = Pelicula.fromJsonList(favJson)

        val esFavorito = favoritos.any { it.titulo == peli.titulo }

        // Cambia el ícono del botón según si es favorito o no
        holder.btnFav.setImageResource(
            if (esFavorito) R.drawable.ic_favorite_check else R.drawable.ic_favorite_border
        )

        // Muestra u oculta el botón según el flag showFavoriteButton
        holder.btnFav.visibility = if (showFavoriteButton) View.VISIBLE else View.GONE

        // Agrega o quita de favoritos al hacer clic en el botón
        holder.btnFav.setOnClickListener {
            val nuevosFavoritos = favoritos.toMutableList()
            if (esFavorito) {
                nuevosFavoritos.removeAll { it.titulo == peli.titulo }
                Toast.makeText(holder.itemView.context, "Quitado de favoritos 💔", Toast.LENGTH_SHORT).show()
            } else {
                nuevosFavoritos.add(peli)
                Toast.makeText(holder.itemView.context, "Agregado a favoritos 💖", Toast.LENGTH_SHORT).show()
            }
            prefs.edit().putString("favoritos", Pelicula.toJsonList(nuevosFavoritos)).apply()
            notifyItemChanged(position)
        }

        // Al hacer clic en el ítem se abre DetallePeliculaActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetallePeliculaActivity::class.java).apply {
                putExtra("titulo", peli.titulo)
                putExtra("imagen", peli.idDrawable)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
}
