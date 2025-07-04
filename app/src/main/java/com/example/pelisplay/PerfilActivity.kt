package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class PerfilActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // 🔐 Obtener correo desde SharedPreferences
        val prefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val correo = prefs.getString("correo", null)

        // Mostrar mensaje de bienvenida
        val textUserName = findViewById<TextView>(R.id.textUserName)
        textUserName.text = correo ?: "Usuario invitado"

        // 🎬 Opción "Mis Favoritos"
        val optionFavorites = findViewById<LinearLayout>(R.id.optionFavorites)
        optionFavorites.setOnClickListener {
            Toast.makeText(this, "Sección de favoritos (pendiente)", Toast.LENGTH_SHORT).show()
        }

        // 🚪 Opción "Cerrar sesión"
        val optionLogout = findViewById<LinearLayout>(R.id.optionLogout)
        optionLogout.setOnClickListener {
            // Borrar datos guardados
            prefs.edit().clear().apply()

            // Redirigir al login
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
