package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// 📝 Pantalla para ingresar nueva película manualmente
class FormularioPeliculaActivity : AppCompatActivity() {

    // Vistas del formulario
    private lateinit var etTitulo: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var spinnerImagen: Spinner
    private lateinit var btnGuardar: Button

    // 🎞️ Lista de imágenes disponibles (nombre y recurso drawable)
    private val imagenesDisponibles = listOf(
        "peli1" to R.drawable.peli1,
        "peli2" to R.drawable.peli2,
        "peli3" to R.drawable.peli3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_pelicula)

        // Asignación de vistas
        etTitulo = findViewById(R.id.etTituloPelicula)
        etDescripcion = findViewById(R.id.etDescripcionPelicula)
        spinnerImagen = findViewById(R.id.spinnerImagen)
        btnGuardar = findViewById(R.id.btnGuardarPelicula)

        // 🎯 Configurar spinner con los nombres de imágenes
        val nombresImagenes = imagenesDisponibles.map { it.first }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresImagenes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerImagen.adapter = adapter

        // 💾 Acción del botón guardar
        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()
            val imagenIndex = spinnerImagen.selectedItemPosition

            // Validaciones
            if (titulo.isEmpty()) {
                Toast.makeText(this, "Escribe el título de la peli", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (descripcion.isEmpty()) {
                Toast.makeText(this, "Agrega una descripción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener imagen seleccionada
            val nombreImagen = imagenesDisponibles[imagenIndex].first
            val idDrawable = imagenesDisponibles[imagenIndex].second

            // Enviar datos a la actividad anterior (AdminPeliculasActivity)
            val intent = Intent()
            intent.putExtra("nuevaPeliculaId", 0) // Puedes reemplazar por un generador de ID si lo necesitas
            intent.putExtra("nuevaPeliculaTitulo", titulo)
            intent.putExtra("nuevaPeliculaDescripcion", descripcion)
            intent.putExtra("nuevaPeliculaNombreImagen", nombreImagen)
            intent.putExtra("nuevaPeliculaImagen", idDrawable)

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
