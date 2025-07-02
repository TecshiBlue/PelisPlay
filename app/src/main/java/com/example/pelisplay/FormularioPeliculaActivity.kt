package com.example.pelisplay

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FormularioPeliculaActivity : AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var spinnerImagen: Spinner
    private lateinit var btnGuardar: Button

    // Simulamos recursos disponibles (nombre del recurso y su ID)
    private val imagenesDisponibles = listOf(
        "peli1" to R.drawable.peli1,
        "peli2" to R.drawable.peli2,
        "peli3" to R.drawable.peli3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_pelicula)

        etTitulo = findViewById(R.id.etTituloPelicula)
        spinnerImagen = findViewById(R.id.spinnerImagen)
        btnGuardar = findViewById(R.id.btnGuardarPelicula)

        // Adaptador para el spinner
        val nombresImagenes = imagenesDisponibles.map { it.first }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresImagenes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerImagen.adapter = adapter

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val imagenIndex = spinnerImagen.selectedItemPosition

            if (titulo.isEmpty()) {
                Toast.makeText(this, "Escribe el título de la peli", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevaPeli = Pelicula(imagenesDisponibles[imagenIndex].second, titulo)

            // 🌟 Enviar de vuelta a AdminActivity
            val intent = intent
            intent.putExtra("nuevaPeliculaImagen", nuevaPeli.imagen)
            intent.putExtra("nuevaPeliculaTitulo", nuevaPeli.titulo)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
