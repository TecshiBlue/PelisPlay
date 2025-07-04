package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FormularioPeliculaActivity : AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etGenero: EditText
    private lateinit var etDuracion: EditText
    private lateinit var etAnio: EditText
    private lateinit var etTrailer: EditText
    private lateinit var spinnerImagen: Spinner
    private lateinit var btnGuardar: Button

    // Lista extendida de imágenes disponibles
    private val imagenesDisponibles = listOf(
        "peli1" to R.drawable.peli1,
        "peli2" to R.drawable.peli2,
        "peli3" to R.drawable.peli3,
        "ficcion1" to R.drawable.ficcion1,
        "ficcion2" to R.drawable.ficcion2,
        "accion6" to R.drawable.accion6,
        "accion7" to R.drawable.accion7,
        "comedia4" to R.drawable.comedia4,
        "drama4" to R.drawable.drama4,
        "terror1" to R.drawable.terror1
        // Agrega más si los tienes en res/drawable
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_pelicula)

        // Vistas del formulario
        etTitulo = findViewById(R.id.etTituloPelicula)
        etDescripcion = findViewById(R.id.etDescripcionPelicula)
        etGenero = findViewById(R.id.etGeneroPelicula)
        etDuracion = findViewById(R.id.etDuracionPelicula)
        etAnio = findViewById(R.id.etAnioPelicula)
        etTrailer = findViewById(R.id.etTrailerPelicula)
        spinnerImagen = findViewById(R.id.spinnerImagen)
        btnGuardar = findViewById(R.id.btnGuardarPelicula)

        val nombresImagenes = imagenesDisponibles.map { it.first }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresImagenes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerImagen.adapter = adapter

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()
            val genero = etGenero.text.toString().trim()
            val duracion = etDuracion.text.toString().trim()
            val anio = etAnio.text.toString().trim()
            val trailer = etTrailer.text.toString().trim()
            val imagenId = imagenesDisponibles[spinnerImagen.selectedItemPosition].second

            if (titulo.isEmpty() || descripcion.isEmpty() || genero.isEmpty() || duracion.isEmpty() || anio.isEmpty() || trailer.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent()
            intent.putExtra("nuevaPeliculaImagen", imagenId)
            intent.putExtra("nuevaPeliculaTitulo", titulo)
            intent.putExtra("nuevaPeliculaDescripcion", descripcion)
            intent.putExtra("nuevaPeliculaGenero", genero)
            intent.putExtra("nuevaPeliculaDuracion", duracion)
            intent.putExtra("nuevaPeliculaAnio", anio)
            intent.putExtra("nuevaPeliculaTrailer", trailer)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
