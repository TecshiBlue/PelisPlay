package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// 👤 PerfilActivity: muestra el perfil del usuario logueado y opciones como favoritos y cerrar sesión
class PerfilActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // 📌 Referencias a los elementos de la interfaz
        val optionFavorites = findViewById<LinearLayout>(R.id.optionFavorites)
        val optionLogout = findViewById<LinearLayout>(R.id.optionLogout)
        val textUserName = findViewById<TextView>(R.id.textUserName)

        // 💾 Recuperar el correo del usuario desde SharedPreferences
        val sharedPrefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val correo = sharedPrefs.getString("correo", "Usuario") ?: "Usuario"

        // 🚫 Si no hay usuario logueado, redirigir al login
        if (correo == "Usuario") {
            Toast.makeText(this, "Primero inicia sesión", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // 👋 Mostrar saludo personalizado al usuario
        textUserName.text = "Hola, $correo ✨"

        // ⭐ Opción para ver películas favoritas
        optionFavorites.setOnClickListener {
            startActivity(Intent(this, FavoritosActivity::class.java))
        }

        // 🚪 Opción para cerrar sesión
        optionLogout.setOnClickListener {
            // Limpiar sesión almacenada
            sharedPrefs.edit().clear().apply()

            Toast.makeText(this, "Sesión cerrada 🥺", Toast.LENGTH_SHORT).show()

            // Redirigir al login y limpiar el back stack
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
