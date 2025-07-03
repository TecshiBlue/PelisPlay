package com.example.pelisplay

import Pelicula

data class GeneroConPeliculas(
    val nombreGenero: String,
    val peliculas: List<Pelicula>
)
