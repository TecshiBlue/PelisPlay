package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Validación de sesión
        val prefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val correo = prefs.getString("correo", null)

        if (correo == null) {
            Toast.makeText(this, "Debes iniciar sesión primero", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // 🟩 Si pasa la validación, mostramos el contenido normal
        setContentView(R.layout.activity_main)

        val searchBar = findViewById<EditText>(R.id.searchBar)

        // 🎞️ Banners
        val bannerViewPager = findViewById<ViewPager2>(R.id.bannerViewPager)
        val banners = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3
        )
        bannerViewPager.adapter = BannerAdapter(banners, bannerViewPager)

        // 🆕 Nuevas pelis
        val recyclerNuevasPelis = findViewById<RecyclerView>(R.id.recyclerNuevasPelis)
        val nuevasPeliculas = listOf(
            Pelicula(R.drawable.romantico1, ""),
            Pelicula(R.drawable.ficcion2, ""),
            Pelicula(R.drawable.culpable1, ""),
            Pelicula(R.drawable.contodo1, ""),
            Pelicula(R.drawable.atrae1, ""),
            Pelicula(R.drawable.ficcion5, ""),
            Pelicula(R.drawable.ficcion6, ""),
            Pelicula(R.drawable.ficcion7, ""),
            Pelicula(R.drawable.ficcion8, "")
        )
        configurarRecyclerComoCarrusel(recyclerNuevasPelis)
        recyclerNuevasPelis.adapter = PeliculaAdapter(nuevasPeliculas)

        // 🎭 Secciones por género
        val actionRecycler = findViewById<RecyclerView>(R.id.actionRecycler)
        val comediaRecycler = findViewById<RecyclerView>(R.id.comediaRecycler)
        val dramaRecycler = findViewById<RecyclerView>(R.id.dramaRecycler)
        val terrorRecycler = findViewById<RecyclerView>(R.id.terrorRecycler)
        val romanceRecycler = findViewById<RecyclerView>(R.id.romanceRecycler)

        configurarRecyclerComoCarrusel(actionRecycler)
        configurarRecyclerComoCarrusel(comediaRecycler)
        configurarRecyclerComoCarrusel(dramaRecycler)
        configurarRecyclerComoCarrusel(terrorRecycler)
        configurarRecyclerComoCarrusel(romanceRecycler)

        val peliculasAccion = listOf(
            Pelicula(R.drawable.ficcion4, "Black Panther"),
            Pelicula(R.drawable.ficcion1, "Avengers"),
            Pelicula(R.drawable.ficcion6, "Rápidos y Furiosos")
        )

        val peliculasComedia = listOf(
            Pelicula(R.drawable.ficcion2, "Frozen"),
            Pelicula(R.drawable.ficcion7, "Encanto"),
            Pelicula(R.drawable.ficcion5, "Toy Story")
        )

        val peliculasDrama = listOf(
            Pelicula(R.drawable.peli4, "Joker"),
            Pelicula(R.drawable.ficcion8, "Inception"),
            Pelicula(R.drawable.ficcion3, "Up")
        )

        val peliculasTerror = listOf(
            Pelicula(R.drawable.terror1, "La Monja"),
            Pelicula(R.drawable.terror2, "El Conjuro"),
            Pelicula(R.drawable.terror3, "It")
        )

        val peliculasRomance = listOf(
            Pelicula(R.drawable.romantico1, "Red, White & Royal Blue"),
            Pelicula(R.drawable.romantico2, "La Idea de Ti"),
            Pelicula(R.drawable.peli7, "Me Before You"),
            Pelicula(R.drawable.culpable1, "Culpa Mia "),
            Pelicula(R.drawable.culpable2, "Culpa Tuya"),
            Pelicula(R.drawable.culpable3, "Culpa Nuestra"),
            Pelicula(R.drawable.atrae1, "A través de mi ventana"),
            Pelicula(R.drawable.atrae2, "A través del mar"),
            Pelicula(R.drawable.atrae3, "A través de tu mirada"),
            Pelicula(R.drawable.contodo1, "Con Todos menos Contigo")
        )

        actionRecycler.adapter = PeliculaAdapter(peliculasAccion)
        comediaRecycler.adapter = PeliculaAdapter(peliculasComedia)
        dramaRecycler.adapter = PeliculaAdapter(peliculasDrama)
        terrorRecycler.adapter = PeliculaAdapter(peliculasTerror)
        romanceRecycler.adapter = PeliculaAdapter(peliculasRomance)

        // 🔁 Botones “Ver más”
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
            abrirListaPorGenero("Romántico") // Asegúrate que coincida con el texto del botón
        }
    }

    private fun configurarRecyclerComoCarrusel(recycler: RecyclerView) {
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.setPadding(60, 0, 60, 0)
        recycler.clipToPadding = false
        recycler.setItemViewCacheSize(10)
    }

    private fun abrirListaPorGenero(genero: String) {
        val intent = Intent(this, GenerosActivity::class.java)
        intent.putExtra("genero", genero)
        startActivity(intent)
    }
}
