package com.example.pelisplay

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFavoritos)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val prefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val favoritos = try {
            val json = prefs.getString("favoritos", "[]") ?: "[]"
            Pelicula.fromJsonList(json)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Ocurrió un error cargando favoritos 🥲", Toast.LENGTH_SHORT).show()
            emptyList()
        }


        if (favoritos.isEmpty()) {
            Toast.makeText(this, "Aún no hay favoritos 💔", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = PeliculaAdapter(favoritos, showFavoriteButton = true)
    }
}
