package com.example.pelisplay

import Pelicula
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GenerosActivity : BaseActivity() {

    private lateinit var adaptador: GeneroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generos)

        val generosRecycler = findViewById<RecyclerView>(R.id.generosRecycler)
        generosRecycler.layoutManager = LinearLayoutManager(this)

        // 🟢 Adaptador inicial con todos los géneros
        adaptador = GeneroAdapter(obtenerTodosLosGeneros())
        generosRecycler.adapter = adaptador

        // 🎭 Botones
        val btnTodo = findViewById<Button>(R.id.btnTodo)
        val btnAccion = findViewById<Button>(R.id.btnAccion)
        val btnComedia = findViewById<Button>(R.id.btnComedia)
        val btnDrama = findViewById<Button>(R.id.btnDrama)
        val btnTerror = findViewById<Button>(R.id.btnTerror)
        val btnAventura = findViewById<Button>(R.id.btnAventura)
        val btnRomantico = findViewById<Button>(R.id.btnRomantico)

        val botones = listOf(
            btnTodo, btnAccion, btnComedia,
            btnDrama, btnTerror, btnAventura, btnRomantico
        )

        botones.forEach { boton ->
            boton.setOnClickListener {
                // Estilo visual
                botones.forEach { it.isSelected = false }
                boton.isSelected = true

                // Filtrado real
                val genero = boton.text.toString()
                val listaFiltrada = when (genero) {
                    "Acción" -> listOf(GeneroConPeliculas("Acción", peliculasAccion()))
                    "Comedia" -> listOf(GeneroConPeliculas("Comedia", peliculasComedia()))
                    "Drama" -> listOf(GeneroConPeliculas("Drama", peliculasDrama()))
                    "Terror" -> listOf(GeneroConPeliculas("Terror", peliculasTerror()))
                    "Aventura" -> listOf(GeneroConPeliculas("Aventura", peliculasAventura()))
                    "Romántico" -> listOf(GeneroConPeliculas("Romántico", peliculasRomanticas()))
                    else -> obtenerTodosLosGeneros()
                }

                // 👇 Aquí sí se actualiza la vista
                adaptador.actualizarLista(listaFiltrada)
            }
        }

        // Si vienes desde MainActivity con un género preseleccionado
        val generoRecibido = intent.getStringExtra("genero")
        generoRecibido?.let {
            val botonCorrespondiente = botones.firstOrNull { btn -> btn.text.toString() == it }
            botonCorrespondiente?.performClick()
        }
    }

    private fun peliculasAccion() = listOf(
        Pelicula(54, R.drawable.accion1, "Misión Imposible"),  // New ID
        Pelicula(55, R.drawable.accion2, "John Wick"),        // New ID
        Pelicula(56, R.drawable.accion3, "Mad Max")          // New ID
    )

    private fun peliculasComedia() = listOf(
        Pelicula(57, R.drawable.comedia1, "Son Como Niños"), // New ID
        Pelicula(58, R.drawable.comedia2, "Ted"),            // New ID
        Pelicula(59, R.drawable.comedia3, "Jumanji")         // New ID
    )

    private fun peliculasDrama() = listOf(
        Pelicula(60, R.drawable.drama1, "El Padrino"),               // New ID
        Pelicula(61, R.drawable.drama2, "En Busca de la Felicidad"), // New ID
        Pelicula(30, R.drawable.drama7, "Forrest Gump")              // Existing ID from reference
    )

    private fun peliculasTerror() = listOf(
        Pelicula(37, R.drawable.terror1, "El Conjuro"),          // Existing ID from reference
        Pelicula(38, R.drawable.terror2, "It"),                 // Existing ID from reference
        Pelicula(43, R.drawable.terror3, "Actividad Paranormal") // Existing ID from reference
    )

    private fun peliculasAventura() = listOf(
        Pelicula(62, R.drawable.aventura1, "Piratas del Caribe"), // New ID
        Pelicula(63, R.drawable.aventura2, "Norbit"),            // New ID
        Pelicula(64, R.drawable.aventura3, "El Hobbit")          // New ID
    )

    private fun peliculasRomanticas() = listOf(
        Pelicula(65, R.drawable.romantico1, "Red, White & Royal Blue"), // New ID
        Pelicula(66, R.drawable.romantico2, "La Idea de Ti"),          // New ID
        Pelicula(49, R.drawable.peli7, "Me Before You")                // Existing ID (from previous assignment)
    )

    private fun obtenerTodosLosGeneros(): List<GeneroConPeliculas> {
        return listOf(
            GeneroConPeliculas("Acción", peliculasAccion()),
            GeneroConPeliculas("Comedia", peliculasComedia()),
            GeneroConPeliculas("Drama", peliculasDrama()),
            GeneroConPeliculas("Terror", peliculasTerror()),
            GeneroConPeliculas("Aventura", peliculasAventura()),
            GeneroConPeliculas("Romántico", peliculasRomanticas())
        )
    }
}
