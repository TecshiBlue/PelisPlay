package com.example.pelisplay

import Pelicula
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
        Pelicula(1, R.drawable.ficcion1, "Avengers"),
        Pelicula(37, R.drawable.terror1, "El Conjuro"),
        Pelicula(5, R.drawable.ficcion4, "Black Panther"),
        Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos"),
        Pelicula(46, R.drawable.peli3, "Rincon del MUndo") // Not in reference, assigned new ID
    )

    private val listaMasReciente = listOf(
        Pelicula(47, R.drawable.peli6, "Purple Hearts"), // Not in reference
        Pelicula(9, R.drawable.ficcion8, "Inception"),
        Pelicula(48, R.drawable.peli4, "Palmer"), // Not in reference (conflict with Joker)
        Pelicula(49, R.drawable.peli7, "Me Before You"), // Not in reference
        Pelicula(8, R.drawable.ficcion7, "Encanto"),
        Pelicula(41, R.drawable.terror6, "La Monja")
    )

    private val listaPopularidad = listOf(
        Pelicula(2, R.drawable.ficcion2, "Frozen"),
        Pelicula(6, R.drawable.ficcion5, "Toy Story"),
        Pelicula(4, R.drawable.ficcion3, "Up"),
        Pelicula(50, R.drawable.peli8, "The Fault in Our Stars"), // Not in reference
        Pelicula(51, R.drawable.peli2, "El Grand Magasin"), // Not in reference
        Pelicula(37, R.drawable.terror2, "El Conjuro") // Same as terror1?
    )

    private val listaRecomendadas = listOf(
        Pelicula(52, R.drawable.peli1, "Tu Color"), // Not in reference
        Pelicula(49, R.drawable.peli7, "Me Before You"), // Same as above
        Pelicula(50, R.drawable.peli8, "The Fault in Our Stars"), // Same as above
        Pelicula(3, R.drawable.peli4, "Jocker"), // Note: Typo "Jocker" vs "Joker"
        Pelicula(46, R.drawable.peli3, "Rincon del MUndo"), // Same as above
        Pelicula(38, R.drawable.terror3, "It")
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
            Pelicula(52, R.drawable.peli1, "Ginny y Georgia"), // Same ID as "Tu Color"?
            Pelicula(51, R.drawable.peli2, "Sin Senos Sí Hay Paraíso"), // Same as "El Grand Magasin"?
            Pelicula(46, R.drawable.peli3, "How to Train Your Dragon"), // Same as "Rincon del MUndo"?
            Pelicula(48, R.drawable.peli4, "Palmer"), // Same as above
            Pelicula(53, R.drawable.peli5, "El Conjuro"), // New ID
            Pelicula(47, R.drawable.peli6, "Purple Hearts"), // Same as above
            Pelicula(49, R.drawable.peli7, "Me Before You"), // Same as above
            Pelicula(50, R.drawable.peli8, "The Fault in Our Stars"), // Same as above
            Pelicula(1, R.drawable.ficcion1, "Avengers"),
            Pelicula(2, R.drawable.ficcion2, "Frozen"),
            Pelicula(4, R.drawable.ficcion3, "Up"),
            Pelicula(5, R.drawable.ficcion4, "Black Panther"),
            Pelicula(6, R.drawable.ficcion5, "Toy Story"),
            Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos"),
            Pelicula(8, R.drawable.ficcion7, "Encanto"),
            Pelicula(9, R.drawable.ficcion8, "Inception"),
            Pelicula(41, R.drawable.terror6, "La Monja"),
            Pelicula(37, R.drawable.terror1, "El Conjuro"), // Or terror2?
            Pelicula(38, R.drawable.terror3, "It")
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
