package com.example.pelisplay

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
        Pelicula(R.drawable.accion1, "Misión Imposible"),
        Pelicula(R.drawable.accion2, "John Wick"),
        Pelicula(R.drawable.accion3, "Mad Max")
    )

    private fun peliculasComedia() = listOf(
        Pelicula(R.drawable.comedia1, "Son Como Niños"),
        Pelicula(R.drawable.comedia2, "Ted"),
        Pelicula(R.drawable.comedia3, "Jumanji")
    )

    private fun peliculasDrama() = listOf(
        Pelicula(R.drawable.drama1, "El Padrino"),
        Pelicula(R.drawable.drama2, "En Busca de la Felicidad"),
        Pelicula(R.drawable.drama3, "Forrest Gump")
    )

    private fun peliculasTerror() = listOf(
        Pelicula(R.drawable.terror1, "El Conjuro"),
        Pelicula(R.drawable.terror2, "It"),
        Pelicula(R.drawable.terror3, "Actividad Paranormal")
    )

    private fun peliculasAventura() = listOf(
        Pelicula(R.drawable.aventura1, "Piratas del Caribe"),
        Pelicula(R.drawable.aventura2, "Norbit"),
        Pelicula(R.drawable.aventura3, "El Hobbit")
    )

    private fun peliculasRomanticas() = listOf(
        Pelicula(R.drawable.romantico1, "Red, White & Royal Blue"),
        Pelicula(R.drawable.romantico2, "La Idea de Ti"),
        Pelicula(R.drawable.peli7, "Me Before You")
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
