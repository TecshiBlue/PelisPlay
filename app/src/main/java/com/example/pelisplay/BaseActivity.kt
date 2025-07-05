package com.example.pelisplay

import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

// 📲 Clase base para todas las actividades con menú inferior
open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        // 1. Inflamos el layout base que contiene el BottomNavigationView y un contenedor principal
        val baseLayout = LayoutInflater.from(this).inflate(R.layout.base_activity, null)

        // 2. Insertamos dentro del contenedor el layout específico de la actividad hija
        val contentFrame = baseLayout.findViewById<FrameLayout>(R.id.main_container)
        LayoutInflater.from(this).inflate(layoutResID, contentFrame, true)

        // 3. Establecemos como vista principal el layout combinado
        super.setContentView(baseLayout)

        // 4. Configuramos el menú inferior de navegación
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 5. Marcamos el ítem activo según la clase actual
        when (this::class.java.simpleName) {
            "MainActivity" -> bottomNav.menu.findItem(R.id.nav_inicio).isChecked = true
            "GenerosActivity" -> bottomNav.menu.findItem(R.id.nav_generos).isChecked = true
            "ListaPeliculasActivity" -> bottomNav.menu.findItem(R.id.nav_lista).isChecked = true
            "PerfilActivity" -> bottomNav.menu.findItem(R.id.nav_perfil).isChecked = true
        }

        // 6. Definimos la lógica de navegación entre actividades
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
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
