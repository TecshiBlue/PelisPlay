package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val optionFavorites = findViewById<LinearLayout>(R.id.optionFavorites)
        val optionLogout = findViewById<LinearLayout>(R.id.optionLogout)
        val textUserName = findViewById<TextView>(R.id.textUserName)

        // 💾 Obtener el correo desde SharedPreferences
        val sharedPrefs = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val correo = sharedPrefs.getString("correo", "Usuario") ?: "Usuario"

        // ✅ Validar si el usuario realmente está logueado
        if (correo == "Usuario") {
            Toast.makeText(this, "Primero inicia sesión", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        textUserName.text = "Hola, $correo ✨"

        // 🎬 Favoritos (por ahora solo mensaje)
        optionFavorites.setOnClickListener {
            Toast.makeText(this, "Vamos a tus favoritos 💕", Toast.LENGTH_SHORT).show()
        }

        // 🚪 Cerrar sesión
        optionLogout.setOnClickListener {
            sharedPrefs.edit().clear().apply()

            Toast.makeText(this, "Sesión cerrada 🥺", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
