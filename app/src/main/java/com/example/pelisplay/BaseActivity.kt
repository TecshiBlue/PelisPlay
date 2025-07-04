package com.example.pelisplay

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.jvm.java

// 📲 BaseActivity: clase base para compartir el menú inferior en todas las actividades
open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        // Inflar el layout base que contiene el menú inferior y un contenedor
        val baseLayout = LayoutInflater.from(this).inflate(R.layout.base_activity, null)

        // Contenedor donde se cargará el contenido de la Activity hija
        val contentFrame = baseLayout.findViewById<FrameLayout>(R.id.main_container)
        LayoutInflater.from(this).inflate(layoutResID, contentFrame, true)

        // Establecer la vista completa
        super.setContentView(baseLayout)

        // Configurar el menú inferior de navegación
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Marcar el ítem actual (opcional, si quieres cambiar visualmente el ítem activo)
        when (this::class.java.simpleName) {
            "MainActivity" -> bottomNav.menu.findItem(R.id.nav_inicio).isChecked = true
            "GenerosActivity" -> bottomNav.menu.findItem(R.id.nav_generos).isChecked = true
            "ListaPeliculasActivity" -> bottomNav.menu.findItem(R.id.nav_lista).isChecked = true
            "PerfilActivity" -> bottomNav.menu.findItem(R.id.nav_perfil).isChecked = true
        }

        // Configuración de navegación
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
