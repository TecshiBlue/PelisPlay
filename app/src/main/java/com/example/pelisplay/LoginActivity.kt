package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class LoginActivity : AppCompatActivity() {

    // 🧾 Mapa con usuarios simulados: clave = correo, valor = (contraseña, rol)
    private val usuariosSimulados = mapOf(
        "admin@pelis.com" to Pair("admin123", "admin"),
        "usuario@pelis.com" to Pair("user123", "usuario")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuarioInput = etUsuario.text.toString().trim()
            val passwordInput = etPassword.text.toString()

            if (usuarioInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val datos = usuariosSimulados[usuarioInput]
            if (datos != null && datos.first == passwordInput) {
                val rol = datos.second
                Toast.makeText(this, "Bienvenido, $rol", Toast.LENGTH_SHORT).show()

                // ✅ Guardar el correo del usuario logueado
                val prefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
                prefs.edit().putString("correo", usuarioInput).apply()

                // 🎯 Redirección según el rol
                val intent = if (rol == "admin") {
                    Intent(this, AdminPeliculasActivity::class.java)
                } else {
                    Intent(this, MainActivity::class.java)
                }

                startActivity(intent)
                finish() // Evita regresar con "atrás"
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
