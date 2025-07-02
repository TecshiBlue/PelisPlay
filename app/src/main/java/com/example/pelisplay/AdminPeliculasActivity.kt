package com.example.pelisplay

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
        Pelicula(R.drawable.ficcion1, "Avengers"),
        Pelicula(R.drawable.ficcion2, "Frozen"),
        Pelicula(R.drawable.peli4, "Joker"),
        Pelicula(R.drawable.ficcion3, "Up"),
        Pelicula(R.drawable.ficcion4, "Black Panther"),
        Pelicula(R.drawable.ficcion5, "Toy Story"),
        Pelicula(R.drawable.ficcion6, "Rapidos y Furiosos"),
        Pelicula(R.drawable.ficcion7, "Encanto"),
        Pelicula(R.drawable.ficcion8, "Inception"),

        // Películas de Acción
        Pelicula(R.drawable.accion6, "Iron Man"),
        Pelicula(R.drawable.accion7, "Capitán América"),
        Pelicula(R.drawable.accion8, "Black Widow"),
        Pelicula(R.drawable.accion9, "Hulk"),
        Pelicula(R.drawable.accion10, "Spider-Man"),
        Pelicula(R.drawable.accion11, "Thor"),
        Pelicula(R.drawable.accion12, "Doctor Strange"),
        Pelicula(R.drawable.accion13, "Guardianes"),
        Pelicula(R.drawable.accion14, "Ant-Man"),

        // Películas de Comedia
        Pelicula(R.drawable.comedia4, "Minions"),
        Pelicula(R.drawable.comedia6, "Shrek"),
        Pelicula(R.drawable.comedia7, "Megamente"),
        Pelicula(R.drawable.comedia8, "Madagascar"),
        Pelicula(R.drawable.comedia9, "Hotel Transylvania"),
        Pelicula(R.drawable.comedia10, "Kung Fu Panda"),
        Pelicula(R.drawable.comedia11, "Los Croods"),
        Pelicula(R.drawable.comedia12, "Jefe en pañales"),
        Pelicula(R.drawable.comedia13, "Vecinos"),

        // Películas de Drama
        Pelicula(R.drawable.drama4, "Titanic"),
        Pelicula(R.drawable.drama6, "Siempre a tu lado"),
        Pelicula(R.drawable.drama7, "Forrest Gump"),
        Pelicula(R.drawable.drama8, "The Blind Side"),
        Pelicula(R.drawable.drama9, "The Notebook"),
        Pelicula(R.drawable.drama10, "Ready or not"),
        Pelicula(R.drawable.drama11, "Milagros del cielo"),
        Pelicula(R.drawable.drama12, "The Other Zoey"),
        Pelicula(R.drawable.drama13, "Enola Holmes"),

        // Películas de Terror
        Pelicula(R.drawable.terror1, "El Conjuro"),
        Pelicula(R.drawable.terror2, "It"),
        Pelicula(R.drawable.terror4, "Annabelle"),
        Pelicula(R.drawable.terror5, "Insidious"),
        Pelicula(R.drawable.terror6, "La Monja"),
        Pelicula(R.drawable.terror7, "Smile"),
        Pelicula(R.drawable.terror3, "Actividad Paranormal"),
        Pelicula(R.drawable.terror8, "La Llorona"),
        Pelicula(R.drawable.terror9, "Destino Final")
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
            val imagen = data?.getIntExtra("nuevaPeliculaImagen", -1) ?: return
            val titulo = data.getStringExtra("nuevaPeliculaTitulo") ?: return

            val nuevaPeli = Pelicula(imagen, titulo)
            listaPeliculas.add(nuevaPeli)
            adaptador.notifyItemInserted(listaPeliculas.size - 1)
        }
    }
}