package com.example.pelisplay

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// 📺 Activity para mostrar los detalles de una película seleccionada
class DetallePeliculaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        // 1. Obtener referencias a las vistas de la interfaz
        val img = findViewById<ImageView>(R.id.imgDetalle)
        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetalle)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtGenero = findViewById<TextView>(R.id.txtGenero)
        val txtDuracion = findViewById<TextView>(R.id.txtDuracion)
        val txtAnio = findViewById<TextView>(R.id.txtAnio)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val btnVerAhora = findViewById<Button>(R.id.btnVerAhora)
        val btnCompartir = findViewById<Button>(R.id.btnCompartir)

        // 2. Recuperar datos enviados desde la pantalla anterior
        val titulo = intent.getStringExtra("titulo") ?: ""
        val imagen = intent.getIntExtra("imagen", 0)

        // 3. Mostrar título e imagen
        txtTitulo.text = titulo
        img.setImageResource(imagen)
        ratingBar.rating = 4.0f  // Valor de calificación por defecto

        // 4. Obtener más detalles desde el repositorio
        val pelicula = PeliculaRepository.obtenerDetallePorTitulo(titulo)

        // 5. Verificar que exista y mostrar sus datos
        if (pelicula != null) {
            txtDescripcion.text = pelicula.descripcion
            txtGenero.text = "Género: ${pelicula.genero}"
            txtDuracion.text = "Duración: ${pelicula.duracion}"
            txtAnio.text = "Año: ${pelicula.anio}"

            // Abrir el tráiler en YouTube
            btnVerAhora.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pelicula.trailerUrl))
                startActivity(intent)
            }

            // Compartir el enlace del tráiler
            btnCompartir.setOnClickListener {
                val compartirIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "¡Mira el tráiler de \"$titulo\" en YouTube! 🎬\n${pelicula.trailerUrl}"
                    )
                }
                startActivity(Intent.createChooser(compartirIntent, "Compartir vía"))
            }

        } else {
            // Mostrar mensaje si no se encontró la película
            Toast.makeText(this, "Película no encontrada", Toast.LENGTH_SHORT).show()
        }
    }
}
