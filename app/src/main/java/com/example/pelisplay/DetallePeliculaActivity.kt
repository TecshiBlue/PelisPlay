package com.example.pelisplay

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetallePeliculaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        // Referencias a las vistas de la interfaz
        val img = findViewById<ImageView>(R.id.imgDetalle)
        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetalle)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtGenero = findViewById<TextView>(R.id.txtGenero)
        val txtDuracion = findViewById<TextView>(R.id.txtDuracion)
        val txtAnio = findViewById<TextView>(R.id.txtAnio)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val btnVerAhora = findViewById<Button>(R.id.btnVerAhora)
        val btnCompartir = findViewById<Button>(R.id.btnCompartir)

        // Obtener datos del intent enviado desde la pantalla anterior
        val titulo = intent.getStringExtra("titulo") ?: ""
        val imagen = intent.getIntExtra("imagen", 0)

        // Mostrar título e imagen de la película
        img.setImageResource(imagen)
        txtTitulo.text = titulo
        ratingBar.rating = 4.0f  // Valor por defecto de calificación

        // Variables para asignar datos de la película
        val pelicula = PeliculaRepository.obtenerDetallePorTitulo(titulo)

        // Mostrar datos en la interfaz
        txtDescripcion.text = pelicula.descripcion
        txtGenero.text = "Género: ${pelicula.genero}"
        txtDuracion.text = "Duración: ${pelicula.duracion}"
        txtAnio.text = "Año: ${pelicula.anio}"

        // Acción para abrir el tráiler en YouTube
        btnVerAhora.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pelicula.trailerUrl))
            startActivity(intent)
        }

        // Acción para compartir el tráiler
        btnCompartir.setOnClickListener {
            val compartirIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "¡Mira el tráiler de \"$titulo\" en YouTube! 🎬\n${pelicula.trailerUrl}")
            }
            startActivity(Intent.createChooser(compartirIntent, "Compartir vía"))
        }
    }
}