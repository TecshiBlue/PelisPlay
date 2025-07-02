package com.example.pelisplay

import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        // 🔧 Inflar el layout base que incluye el BottomNavigationView
        val baseView = LayoutInflater.from(this).inflate(R.layout.base_activity, null)
        val contentFrame = baseView.findViewById<FrameLayout>(R.id.main_container)

        // 🔧 Inflar el layout específico de la actividad hija dentro del contenedor
        LayoutInflater.from(this).inflate(layoutResID, contentFrame, true)

        // ⚙️ Establecer el layout base como contenido principal
        super.setContentView(baseView)

        // 📍 Configurar el menú inferior
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // ✅ Marcar el ítem seleccionado según la clase actual
        when (this) {
            is MainActivity -> bottomNav.selectedItemId = R.id.nav_inicio
            is GenerosActivity -> bottomNav.selectedItemId = R.id.nav_generos
            is ListaPeliculasActivity -> bottomNav.selectedItemId = R.id.nav_lista
            is PerfilActivity -> bottomNav.selectedItemId = R.id.nav_perfil
        }

        // 🔄 Manejar la navegación
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_inicio -> {
                    if (this !is MainActivity) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    true
                }
                R.id.nav_generos -> {
                    if (this !is GenerosActivity) {
                        startActivity(Intent(this, GenerosActivity::class.java))
                        finish()
                    }
                    true
                }
                R.id.nav_lista -> {
                    if (this !is ListaPeliculasActivity) {
                        startActivity(Intent(this, ListaPeliculasActivity::class.java))
                        finish()
                    }
                    true
                }
                R.id.nav_perfil -> {
                    if (this !is PerfilActivity) {
                        startActivity(Intent(this, PerfilActivity::class.java))
                        finish()
                    }
                    true
                }
                else -> false
            }
        }
    }
}
