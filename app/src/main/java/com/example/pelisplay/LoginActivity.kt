package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// 🎬 LoginActivity: permite al usuario autenticarse como 'admin' o 'usuario'
class LoginActivity : AppCompatActivity() {

    // 🧾 Usuarios simulados con su contraseña y rol correspondiente
    private val usuariosSimulados = mapOf(
        "admin@pelis.com" to Pair("admin123", "admin"),
        "usuario@pelis.com" to Pair("user123", "usuario")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los campos de la vista
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Acción al presionar el botón de login
        btnLogin.setOnClickListener {
            val usuarioInput = etUsuario.text.toString().trim()
            val passwordInput = etPassword.text.toString()

            // Validación de campos vacíos
            if (usuarioInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar usuario contra el mapa de datos
            val datos = usuariosSimulados[usuarioInput]
            if (datos != null && datos.first == passwordInput) {
                val rol = datos.second
                Toast.makeText(this, "Bienvenido, $rol", Toast.LENGTH_SHORT).show()

                // Guardar sesión simulada con SharedPreferences
                val prefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
                prefs.edit().putString("correo", usuarioInput).apply()

                // Redirigir según el rol
                val intent = if (rol == "admin") {
                    Intent(this, AdminPeliculasActivity::class.java)
                } else {
                    Intent(this, MainActivity::class.java)
                }

                startActivity(intent)
                finish() // Para evitar volver al login con botón atrás
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
