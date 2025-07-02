package com.example.pelisplay

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class ListaPeliculasActivity : BaseActivity() {

    // Referencias a la vista: RecyclerView y TabLayouts
    private lateinit var recycler: RecyclerView
    private lateinit var tabsHot: TabLayout
    private lateinit var tabsFiltro: TabLayout

    // Lista general de todas las películas disponibles
    private var peliculasTotales: List<Pelicula> = emptyList()

    // 🔥 Lista de películas destacadas por categoría
    private val listaTopHoy = listOf(
        Pelicula(R.drawable.ficcion1, "Avengers"),
        Pelicula(R.drawable.terror1, "El Conjuro"),
        Pelicula(R.drawable.ficcion4, "Black Panther"),
        Pelicula(R.drawable.ficcion6, "Rápidos y Furiosos"),
        Pelicula(R.drawable.peli3, "Rincon del MUndo")
    )

    private val listaMasReciente = listOf(
        Pelicula(R.drawable.peli6, "Purple Hearts"),
        Pelicula(R.drawable.ficcion8, "Inception"),
        Pelicula(R.drawable.peli4, "Palmer"),
        Pelicula(R.drawable.peli7, "Me Before You"),
        Pelicula(R.drawable.ficcion7, "Encanto"),
        Pelicula(R.drawable.terror1, "La Monja")
    )

    private val listaPopularidad = listOf(
        Pelicula(R.drawable.ficcion2, "Frozen"),
        Pelicula(R.drawable.ficcion5, "Toy Story"),
        Pelicula(R.drawable.ficcion3, "Up"),
        Pelicula(R.drawable.peli8, "The Fault in Our Stars"),
        Pelicula(R.drawable.peli2, "El Grand Magasin"),
        Pelicula(R.drawable.terror2, "El Conjuro")
    )

    private val listaRecomendadas = listOf(
        Pelicula(R.drawable.peli1, "Tu Color"),
        Pelicula(R.drawable.peli7, "Me Before You"),
        Pelicula(R.drawable.peli8, "The Fault in Our Stars"),
        Pelicula(R.drawable.peli4, "Jocker"),
        Pelicula(R.drawable.peli3, "Rincon del MUndo"),
        Pelicula(R.drawable.terror3, "It")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_peliculas)

        // Vinculación de vistas
        tabsHot = findViewById(R.id.tabsHot)
        tabsFiltro = findViewById(R.id.tabsFiltro)
        recycler = findViewById(R.id.recyclerPeliculas)

        // 🔍 Obtener género recibido (aunque no se usa en este caso)
        val genero = intent.getStringExtra("genero") ?: "Todo"

        // 🎞 Lista inicial completa
        peliculasTotales = listOf(
            Pelicula(R.drawable.peli1, "Ginny y Georgia"),
            Pelicula(R.drawable.peli2, "Sin Senos Sí Hay Paraíso"),
            Pelicula(R.drawable.peli3, "How to Train Your Dragon"),
            Pelicula(R.drawable.peli4, "Palmer"),
            Pelicula(R.drawable.peli5, "El Conjuro"),
            Pelicula(R.drawable.peli6, "Purple Hearts"),
            Pelicula(R.drawable.peli7, "Me Before You"),
            Pelicula(R.drawable.peli8, "The Fault in Our Stars"),
            Pelicula(R.drawable.ficcion1, "Avengers"),
            Pelicula(R.drawable.ficcion2, "Frozen"),
            Pelicula(R.drawable.ficcion3, "Up"),
            Pelicula(R.drawable.ficcion4, "Black Panther"),
            Pelicula(R.drawable.ficcion5, "Toy Story"),
            Pelicula(R.drawable.ficcion6, "Rápidos y Furiosos"),
            Pelicula(R.drawable.ficcion7, "Encanto"),
            Pelicula(R.drawable.ficcion8, "Inception"),
            Pelicula(R.drawable.terror1, "La Monja"),
            Pelicula(R.drawable.terror2, "El Conjuro"),
            Pelicula(R.drawable.terror3, "It")
        )

        // 💠 Configurar RecyclerView con layout en forma de cuadrícula (3 columnas)
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = PeliculaAdapter(peliculasTotales)

        // ⚙️ Configura los tabs con su lógica
        configurarTabs()
    }

    // 🔁 Configura las pestañas (tabs) y qué hacen cuando se seleccionan
    private fun configurarTabs() {
        // Pestañas superiores: categorías destacadas
        val tabsHotList = listOf("Top hoy", "Más reciente", "Popularidad", "Recomendadas")
        tabsHotList.forEach {
            tabsHot.addTab(tabsHot.newTab().setText(it))
        }

        // Pestañas inferiores: filtros generales
        val filtros = listOf("Todo", "Películas")
        filtros.forEach {
            tabsFiltro.addTab(tabsFiltro.newTab().setText(it))
        }

        // 🎯 Acción al seleccionar una pestaña de filtros
        tabsFiltro.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Actualmente solo recarga la lista completa
                // Puedes añadir lógica adicional si quieres filtrar por tipo
                actualizarLista(peliculasTotales)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // 🔥 Acción al seleccionar una pestaña de "Hot"
        tabsHot.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.text.toString()) {
                    "Top hoy" -> actualizarLista(listaTopHoy)
                    "Más reciente" -> actualizarLista(listaMasReciente)
                    "Popularidad" -> actualizarLista(listaPopularidad)
                    "Recomendadas" -> actualizarLista(listaRecomendadas)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // 🔄 Actualiza el contenido que se muestra en el RecyclerView
    private fun actualizarLista(lista: List<Pelicula>) {
        recycler.adapter = PeliculaAdapter(lista)
    }
}
