package com.example.pelisplay

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// 🎬 Modelo de datos que representa una película
data class Pelicula(
    val id: Int,                  // ID único de la película
    val idDrawable: Int,          // ID del recurso de imagen (R.drawable.xxx)
    val titulo: String,           // Título de la película
    val nombreImagen: String = "",// Nombre de la imagen (opcional, usado en formularios)
    val descripcion: String = ""  // Descripción de la película (opcional)
) {
    companion object {

        // 🔄 Convierte un JSON (formato de texto) a una lista de objetos Pelicula
        fun fromJsonList(json: String): List<Pelicula> {
            val type = object : TypeToken<List<Pelicula>>() {}.type
            return Gson().fromJson(json, type)
        }

        // 🔄 Convierte una lista de películas a un string JSON (por ejemplo, para guardar en SharedPreferences)
        fun toJsonList(lista: List<Pelicula>): String {
            return Gson().toJson(lista)
        }
    }
}
