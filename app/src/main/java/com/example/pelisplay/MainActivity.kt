package com.example.pelisplay

import Pelicula
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
            Pelicula(1, R.drawable.ficcion1, "Avengers"),
            Pelicula(2, R.drawable.ficcion2, "Frozen"),
            Pelicula(3, R.drawable.peli4, "Joker"),
            Pelicula(4, R.drawable.ficcion3, "Up"),
            Pelicula(5, R.drawable.ficcion4, "Black Panther"),
            Pelicula(6, R.drawable.ficcion5, "Toy Story"),
            Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos"),
            Pelicula(8, R.drawable.ficcion7, "Encanto"),
            Pelicula(9, R.drawable.ficcion8, "Inception")
        )
        configurarRecyclerComoCarrusel(recyclerNuevasPelis)
        recyclerNuevasPelis.adapter = PeliculaAdapter(nuevasPeliculas)

// 🎭 Secciones por género
        val actionRecycler = findViewById<RecyclerView>(R.id.actionRecycler)
        val comediaRecycler = findViewById<RecyclerView>(R.id.comediaRecycler)
        val dramaRecycler = findViewById<RecyclerView>(R.id.dramaRecycler)
        val terrorRecycler = findViewById<RecyclerView>(R.id.terrorRecycler)

        configurarRecyclerComoCarrusel(actionRecycler)
        configurarRecyclerComoCarrusel(comediaRecycler)
        configurarRecyclerComoCarrusel(dramaRecycler)
        configurarRecyclerComoCarrusel(terrorRecycler)

        val peliculasAccion = listOf(
            Pelicula(5, R.drawable.ficcion4, "Black Panther"),
            Pelicula(1, R.drawable.ficcion1, "Avengers"),
            Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos"),
            Pelicula(10, R.drawable.accion6, "Iron Man"),
            Pelicula(11, R.drawable.accion7, "Capitán América"),
            Pelicula(12, R.drawable.accion8, "Black Widow")
        )

        val peliculasComedia = listOf(
            Pelicula(2, R.drawable.ficcion2, "Frozen"),
            Pelicula(8, R.drawable.ficcion7, "Encanto"),
            Pelicula(6, R.drawable.ficcion5, "Toy Story"),
            Pelicula(19, R.drawable.comedia4, "Minions"),
            Pelicula(20, R.drawable.comedia6, "Shrek"),
            Pelicula(21, R.drawable.comedia7, "Megamente")
        )

        val peliculasDrama = listOf(
            Pelicula(3, R.drawable.peli4, "Joker"),
            Pelicula(9, R.drawable.ficcion8, "Inception"),
            Pelicula(4, R.drawable.ficcion3, "Up"),
            Pelicula(28, R.drawable.drama4, "Titanic"),
            Pelicula(29, R.drawable.drama6, "Siempre a tu lado"),
            Pelicula(30, R.drawable.drama7, "Forrest Gump")
        )

        val peliculasTerror = listOf(
            Pelicula(41, R.drawable.terror6, "La Monja"),
            Pelicula(37, R.drawable.terror1, "El Conjuro"),
            Pelicula(38, R.drawable.terror2, "It"),
            Pelicula(39, R.drawable.terror4, "Annabelle"),
            Pelicula(40, R.drawable.terror5, "Insidious"),
            Pelicula(42, R.drawable.terror7, "Smile")
        )

        actionRecycler.adapter = PeliculaAdapter(peliculasAccion)
        comediaRecycler.adapter = PeliculaAdapter(peliculasComedia)
        dramaRecycler.adapter = PeliculaAdapter(peliculasDrama)
        terrorRecycler.adapter = PeliculaAdapter(peliculasTerror)

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
