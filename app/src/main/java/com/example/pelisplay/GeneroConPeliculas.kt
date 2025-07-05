package com.example.pelisplay

// 🧩 Modelo que representa un género con su lista de películas asociadas
data class GeneroConPeliculas(
    val nombreGenero: String,        // Ejemplo: "Acción", "Comedia"
    val peliculas: List<Pelicula>    // Lista de películas que pertenecen a ese género
)
