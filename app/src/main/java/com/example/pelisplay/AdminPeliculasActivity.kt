package com.example.pelisplay

import Pelicula
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminPeliculasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAgregar: Button
    private lateinit var adaptador: PeliculaAdminAdapter

    private val listaPeliculas = mutableListOf(
        // Películas recientes
        Pelicula(1, R.drawable.ficcion1, "Avengers"),
        Pelicula(2, R.drawable.ficcion2, "Frozen"),
        Pelicula(3, R.drawable.peli4, "Joker"),
        Pelicula(4, R.drawable.ficcion3, "Up"),
        Pelicula(5, R.drawable.ficcion4, "Black Panther"),
        Pelicula(6, R.drawable.ficcion5, "Toy Story"),
        Pelicula(7, R.drawable.ficcion6, "Rapidos y Furiosos"),
        Pelicula(8, R.drawable.ficcion7, "Encanto"),
        Pelicula(9, R.drawable.ficcion8, "Inception"),

        // Películas de Acción
        Pelicula(10, R.drawable.accion6, "Iron Man"),
        Pelicula(11, R.drawable.accion7, "Capitán América"),
        Pelicula(12, R.drawable.accion8, "Black Widow"),
        Pelicula(13, R.drawable.accion9, "Hulk"),
        Pelicula(14, R.drawable.accion10, "Spider-Man"),
        Pelicula(15, R.drawable.accion11, "Thor"),
        Pelicula(16, R.drawable.accion12, "Doctor Strange"),
        Pelicula(17, R.drawable.accion13, "Guardianes"),
        Pelicula(18, R.drawable.accion14, "Ant-Man"),

        // Películas de Comedia
        Pelicula(19, R.drawable.comedia4, "Minions"),
        Pelicula(20, R.drawable.comedia6, "Shrek"),
        Pelicula(21, R.drawable.comedia7, "Megamente"),
        Pelicula(22, R.drawable.comedia8, "Madagascar"),
        Pelicula(23, R.drawable.comedia9, "Hotel Transylvania"),
        Pelicula(24, R.drawable.comedia10, "Kung Fu Panda"),
        Pelicula(25, R.drawable.comedia11, "Los Croods"),
        Pelicula(26, R.drawable.comedia12, "Jefe en pañales"),
        Pelicula(27, R.drawable.comedia13, "Vecinos"),

        // Películas de Drama
        Pelicula(28, R.drawable.drama4, "Titanic"),
        Pelicula(29, R.drawable.drama6, "Siempre a tu lado"),
        Pelicula(30, R.drawable.drama7, "Forrest Gump"),
        Pelicula(31, R.drawable.drama8, "The Blind Side"),
        Pelicula(32, R.drawable.drama9, "The Notebook"),
        Pelicula(33, R.drawable.drama10, "Ready or not"),
        Pelicula(34, R.drawable.drama11, "Milagros del cielo"),
        Pelicula(35, R.drawable.drama12, "The Other Zoey"),
        Pelicula(36, R.drawable.drama13, "Enola Holmes"),

        // Películas de Terror
        Pelicula(37, R.drawable.terror1, "El Conjuro"),
        Pelicula(38, R.drawable.terror2, "It"),
        Pelicula(39, R.drawable.terror4, "Annabelle"),
        Pelicula(40, R.drawable.terror5, "Insidious"),
        Pelicula(41, R.drawable.terror6, "La Monja"),
        Pelicula(42, R.drawable.terror7, "Smile"),
        Pelicula(43, R.drawable.terror3, "Actividad Paranormal"),
        Pelicula(44, R.drawable.terror8, "La Llorona"),
        Pelicula(45, R.drawable.terror9, "Destino Final")
    )


    private val NUEVA_PELICULA_REQUEST = 1 // 🚨 importante para saber de dónde viene el resultado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_peliculas)

        recyclerView = findViewById(R.id.recyclerPeliculasAdmin)
        btnAgregar = findViewById(R.id.btnAgregarPelicula)

        adaptador = PeliculaAdminAdapter(listaPeliculas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador

        btnAgregar.setOnClickListener {
            // 🌟 Llamamos al formulario y esperamos resultado
            val intent = Intent(this, FormularioPeliculaActivity::class.java)
            startActivityForResult(intent, NUEVA_PELICULA_REQUEST)
        }
    }

    // 🎁 Recibimos la nueva peli desde el formulario
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NUEVA_PELICULA_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra("nuevaPeliculaId", -1) ?: return
            val imagen = data.getIntExtra("nuevaPeliculaImagen", -1)
            val titulo = data.getStringExtra("nuevaPeliculaTitulo") ?: return

            val nuevaPeli = Pelicula(id, imagen, titulo)
            listaPeliculas.add(nuevaPeli)
            adaptador.notifyItemInserted(listaPeliculas.size - 1)
        }
    }


}