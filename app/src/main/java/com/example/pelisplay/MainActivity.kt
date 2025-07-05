package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

// 🎬 MainActivity: pantalla principal con banners, nuevas pelis y secciones por género
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Validar si el usuario está logueado
        val prefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val correo = prefs.getString("correo", null)

        if (correo == null) {
            Toast.makeText(this, "Debes iniciar sesión primero", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // 🟢 Mostrar interfaz si pasa validación
        setContentView(R.layout.activity_main)

        // 🔍 Barra de búsqueda (opcional)
        val searchBar = findViewById<EditText>(R.id.searchBar)

        // 🎞️ Banners superiores con ViewPager2
        val bannerViewPager = findViewById<ViewPager2>(R.id.bannerViewPager)
        val banners = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
        bannerViewPager.adapter = BannerAdapter(banners, bannerViewPager)

        // 🆕 Sección de nuevas películas
        val recyclerNuevasPelis = findViewById<RecyclerView>(R.id.recyclerNuevasPelis)
        val nuevasPeliculas = listOf(
            Pelicula(1, R.drawable.culpable1, ""),
            Pelicula(2, R.drawable.ficcion2, ""),
            Pelicula(3, R.drawable.atrae2, ""),
            Pelicula(4, R.drawable.ficcion3, ""),
            Pelicula(5, R.drawable.ficcion4, ""),
            Pelicula(6, R.drawable.contodo1, ""),
            Pelicula(7, R.drawable.ficcion6, ""),
            Pelicula(8, R.drawable.ficcion7, ""),
            Pelicula(9, R.drawable.ficcion8, ""),
            Pelicula(10, R.drawable.romantico1, ""),
            Pelicula(11, R.drawable.terror9, "")
        )
        configurarRecyclerComoCarrusel(recyclerNuevasPelis)
        recyclerNuevasPelis.adapter = PeliculaAdapter(nuevasPeliculas)

        // 🎭 Secciones por género (incluye Romance)
        val actionRecycler = findViewById<RecyclerView>(R.id.actionRecycler)
        val comediaRecycler = findViewById<RecyclerView>(R.id.comediaRecycler)
        val dramaRecycler = findViewById<RecyclerView>(R.id.dramaRecycler)
        val terrorRecycler = findViewById<RecyclerView>(R.id.terrorRecycler)
        val romanticoRecycler = findViewById<RecyclerView>(R.id.romanceRecycler)

        configurarRecyclerComoCarrusel(actionRecycler)
        configurarRecyclerComoCarrusel(comediaRecycler)
        configurarRecyclerComoCarrusel(dramaRecycler)
        configurarRecyclerComoCarrusel(terrorRecycler)
        configurarRecyclerComoCarrusel(romanticoRecycler)

        // 🧩 Películas por género
        val peliculasAccion = listOf(
            Pelicula(5, R.drawable.ficcion4, "Black Panther"),
            Pelicula(1, R.drawable.ficcion1, "Avengers"),
            Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos")
        )

        val peliculasComedia = listOf(
            Pelicula(2, R.drawable.ficcion2, "Frozen"),
            Pelicula(8, R.drawable.ficcion7, "Encanto"),
            Pelicula(6, R.drawable.ficcion5, "Toy Story")
        )

        val peliculasDrama = listOf(
            Pelicula(3, R.drawable.peli4, "Joker"),
            Pelicula(9, R.drawable.ficcion8, "Inception"),
            Pelicula(4, R.drawable.ficcion3, "Up")
        )

        val peliculasTerror = listOf(
            Pelicula(41, R.drawable.terror6, "La Monja"),
            Pelicula(37, R.drawable.terror1, "El Conjuro"),
            Pelicula(38, R.drawable.terror2, "It")
        )

        val peliculasRomanticas = listOf(
            Pelicula(50, R.drawable.romantico1, "Red, White & Royal Blue"),
            Pelicula(51, R.drawable.romantico2, "La Idea de Ti"),
            Pelicula(52, R.drawable.peli7, "Me Before You"),
            Pelicula(53, R.drawable.peli8, "The Fault in Our Stars"),
            Pelicula(54, R.drawable.culpable1, "Culpa Mía"),
            Pelicula(55, R.drawable.contodo1, "Con Todos menos Contigo")
        )

        // 🎥 Asignar adaptadores a cada sección
        actionRecycler.adapter = PeliculaAdapter(peliculasAccion)
        comediaRecycler.adapter = PeliculaAdapter(peliculasComedia)
        dramaRecycler.adapter = PeliculaAdapter(peliculasDrama)
        terrorRecycler.adapter = PeliculaAdapter(peliculasTerror)
        romanticoRecycler.adapter = PeliculaAdapter(peliculasRomanticas)

        // 🔁 Botones "Ver más" para ir a la lista de cada género
        findViewById<Button>(R.id.btnVerMasAccion).setOnClickListener {
            abrirListaPorGenero("Acción")
        }

        findViewById<Button>(R.id.btnVerMasComedia).setOnClickListener {
            abrirListaPorGenero("Comedia")
        }

        findViewById<Button>(R.id.btnVerMasDrama).setOnClickListener {
            abrirListaPorGenero("Drama")
        }

        findViewById<Button>(R.id.btnVerMasTerror).setOnClickListener {
            abrirListaPorGenero("Terror")
        }

        findViewById<Button>(R.id.btnVerMasRomance).setOnClickListener {
            abrirListaPorGenero("Romántico")
        }
    }

    // 🎠 Configura cada RecyclerView como carrusel horizontal
    private fun configurarRecyclerComoCarrusel(recycler: RecyclerView) {
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.setPadding(60, 0, 60, 0)
        recycler.clipToPadding = false
        recycler.setItemViewCacheSize(10)
    }

    // 🔗 Abre la pantalla de géneros y envía el nombre del género seleccionado
    private fun abrirListaPorGenero(genero: String) {
        val intent = Intent(this, GenerosActivity::class.java)
        intent.putExtra("genero", genero)
        startActivity(intent)
    }
}
