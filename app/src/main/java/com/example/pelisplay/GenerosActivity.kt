package com.example.pelisplay

import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 🎭 GenerosActivity: muestra películas filtradas por género
class GenerosActivity : BaseActivity() {

    private lateinit var adaptador: GeneroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generos)

        val generosRecycler = findViewById<RecyclerView>(R.id.generosRecycler)
        generosRecycler.layoutManager = LinearLayoutManager(this)

        // 🟢 Mostrar todos los géneros al inicio
        adaptador = GeneroAdapter(obtenerTodosLosGeneros())
        generosRecycler.adapter = adaptador

        // 🎯 Botones para filtrar por género
        val btnTodo = findViewById<Button>(R.id.btnTodo)
        val btnAccion = findViewById<Button>(R.id.btnAccion)
        val btnComedia = findViewById<Button>(R.id.btnComedia)
        val btnDrama = findViewById<Button>(R.id.btnDrama)
        val btnTerror = findViewById<Button>(R.id.btnTerror)
        val btnAventura = findViewById<Button>(R.id.btnAventura)
        val btnRomance = findViewById<Button>(R.id.btnRomance)

        val botones = listOf(
            btnTodo, btnAccion, btnComedia,
            btnDrama, btnTerror, btnAventura, btnRomance
        )

        // 🧩 Acción de filtrado por botón
        botones.forEach { boton ->
            boton.setOnClickListener {
                botones.forEach { it.isSelected = false }
                boton.isSelected = true

                val genero = boton.text.toString()
                val listaFiltrada = when (genero) {
                    "Acción" -> listOf(GeneroConPeliculas("Acción", peliculasAccion()))
                    "Comedia" -> listOf(GeneroConPeliculas("Comedia", peliculasComedia()))
                    "Drama" -> listOf(GeneroConPeliculas("Drama", peliculasDrama()))
                    "Terror" -> listOf(GeneroConPeliculas("Terror", peliculasTerror()))
                    "Aventura" -> listOf(GeneroConPeliculas("Aventura", peliculasAventura()))
                    "Romance" -> listOf(GeneroConPeliculas("Romance", peliculasRomance()))
                    else -> obtenerTodosLosGeneros()
                }

                adaptador.actualizarLista(listaFiltrada)
            }
        }

        // 🎯 Si vino con género preseleccionado desde MainActivity
        val generoRecibido = intent.getStringExtra("genero")
        generoRecibido?.let {
            val botonCorrespondiente = botones.firstOrNull { btn -> btn.text.toString() == it }
            botonCorrespondiente?.performClick()
        }
    }

    // 🎬 Películas por género

    private fun peliculasAccion() = listOf(
        Pelicula(54, R.drawable.accion1, "Misión Imposible"),
        Pelicula(55, R.drawable.accion2, "John Wick"),
        Pelicula(56, R.drawable.accion3, "Mad Max")
    )

    private fun peliculasComedia() = listOf(
        Pelicula(57, R.drawable.comedia1, "Son Como Niños"),
        Pelicula(58, R.drawable.comedia2, "Ted"),
        Pelicula(59, R.drawable.comedia3, "Jumanji")
    )

    private fun peliculasDrama() = listOf(
        Pelicula(60, R.drawable.drama1, "El Padrino"),
        Pelicula(61, R.drawable.drama2, "En Busca de la Felicidad"),
        Pelicula(30, R.drawable.drama7, "Forrest Gump")
    )

    private fun peliculasTerror() = listOf(
        Pelicula(37, R.drawable.terror1, "El Conjuro"),
        Pelicula(38, R.drawable.terror2, "It"),
        Pelicula(43, R.drawable.terror3, "Actividad Paranormal")
    )

    private fun peliculasAventura() = listOf(
        Pelicula(62, R.drawable.aventura1, "Piratas del Caribe"),
        Pelicula(63, R.drawable.aventura2, "Norbit"),
        Pelicula(64, R.drawable.aventura3, "El Hobbit")
    )

    private fun peliculasRomance() = listOf(
        Pelicula(65, R.drawable.romantico1, "Red, White & Royal Blue"),
        Pelicula(66, R.drawable.romantico2, "La Idea de Ti"),
        Pelicula(49, R.drawable.peli7, "Me Before You"),
        Pelicula(67, R.drawable.culpable1, "Culpa Mía"),
        Pelicula(68, R.drawable.contodo1, "Con Todos menos Contigo")
    )

    // 🔁 Mostrar todas las categorías
    private fun obtenerTodosLosGeneros(): List<GeneroConPeliculas> {
        return listOf(
            GeneroConPeliculas("Acción", peliculasAccion()),
            GeneroConPeliculas("Comedia", peliculasComedia()),
            GeneroConPeliculas("Drama", peliculasDrama()),
            GeneroConPeliculas("Terror", peliculasTerror()),
            GeneroConPeliculas("Aventura", peliculasAventura()),
            GeneroConPeliculas("Romance", peliculasRomance())
        )
    }
}
