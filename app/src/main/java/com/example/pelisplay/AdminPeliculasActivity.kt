package com.example.pelisplay

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdminPeliculasActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var btn: Button
    private lateinit var adapter: PeliculaAdminAdapter

    private val listaPeliculas = mutableListOf(
        Pelicula(1, R.drawable.peli1, "Título 1", "peli1", "Descripción 1"),
        Pelicula(2, R.drawable.peli2, "Título 2", "peli2", "Descripción 2")
    )

    private val REQ = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_peliculas)

        rv = findViewById(R.id.recyclerPeliculasAdmin)
        btn = findViewById(R.id.btnAgregarPelicula)

        adapter = PeliculaAdminAdapter(listaPeliculas)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        btn.setOnClickListener {
            startActivityForResult(Intent(this, FormularioPeliculaActivity::class.java), REQ)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ && resultCode == Activity.RESULT_OK && data != null) {
            val id = data.getIntExtra("nuevaPeliculaId", 0)
            val titulo = data.getStringExtra("nuevaPeliculaTitulo") ?: ""
            val descripcion = data.getStringExtra("nuevaPeliculaDescripcion") ?: ""
            val nombreImg = data.getStringExtra("nuevaPeliculaNombreImagen") ?: ""
            val idDrawable = data.getIntExtra("nuevaPeliculaImagen", 0)

            val nueva = Pelicula(id, idDrawable, titulo, nombreImg, descripcion)
            listaPeliculas.add(nueva)
            adapter.notifyItemInserted(listaPeliculas.size - 1)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
