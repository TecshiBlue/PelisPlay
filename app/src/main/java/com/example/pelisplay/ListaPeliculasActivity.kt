package com.example.pelisplay

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

// 🎬 ListaPeliculasActivity muestra una cuadrícula de películas con filtros y pestañas destacadas
class ListaPeliculasActivity : BaseActivity() {

    // Componentes de la vista
    private lateinit var recycler: RecyclerView
    private lateinit var tabsHot: TabLayout
    private lateinit var tabsFiltro: TabLayout

    // Lista general de todas las películas disponibles
    private var peliculasTotales: List<Pelicula> = emptyList()

    // 🔥 Categorías destacadas (tabs superiores)
    private val listaTopHoy = listOf(
        Pelicula(1, R.drawable.ficcion1, "Avengers"),
        Pelicula(37, R.drawable.terror1, "El Conjuro"),
        Pelicula(5, R.drawable.ficcion4, "Black Panther"),
        Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos"),
        Pelicula(46, R.drawable.peli3, "Rincon del MUndo")
    )

    private val listaMasReciente = listOf(
        Pelicula(47, R.drawable.peli6, "Purple Hearts"),
        Pelicula(9, R.drawable.ficcion8, "Inception"),
        Pelicula(48, R.drawable.peli4, "Palmer"),
        Pelicula(49, R.drawable.peli7, "Me Before You"),
        Pelicula(8, R.drawable.ficcion7, "Encanto"),
        Pelicula(41, R.drawable.terror6, "La Monja")
    )

    private val listaPopularidad = listOf(
        Pelicula(2, R.drawable.ficcion2, "Frozen"),
        Pelicula(6, R.drawable.ficcion5, "Toy Story"),
        Pelicula(4, R.drawable.ficcion3, "Up"),
        Pelicula(50, R.drawable.peli8, "The Fault in Our Stars"),
        Pelicula(51, R.drawable.peli2, "El Grand Magasin"),
        Pelicula(37, R.drawable.terror2, "El Conjuro")
    )

    private val listaRecomendadas = listOf(
        Pelicula(52, R.drawable.peli1, "Tu Color"),
        Pelicula(49, R.drawable.peli7, "Me Before You"),
        Pelicula(50, R.drawable.peli8, "The Fault in Our Stars"),
        Pelicula(3, R.drawable.peli4, "Jocker"),
        Pelicula(46, R.drawable.peli3, "Rincon del MUndo"),
        Pelicula(38, R.drawable.terror3, "It")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_peliculas)

        // 🔗 Vincular vistas con el layout XML
        tabsHot = findViewById(R.id.tabsHot)
        tabsFiltro = findViewById(R.id.tabsFiltro)
        recycler = findViewById(R.id.recyclerPeliculas)

        // 📥 Recuperar películas completas (todo el catálogo)
        peliculasTotales = listOf(
            Pelicula(52, R.drawable.peli1, "Ginny y Georgia"),
            Pelicula(51, R.drawable.peli2, "Sin Senos Sí Hay Paraíso"),
            Pelicula(46, R.drawable.peli3, "How to Train Your Dragon"),
            Pelicula(48, R.drawable.peli4, "Palmer"),
            Pelicula(53, R.drawable.peli5, "El Conjuro"),
            Pelicula(47, R.drawable.peli6, "Purple Hearts"),
            Pelicula(49, R.drawable.peli7, "Me Before You"),
            Pelicula(50, R.drawable.peli8, "The Fault in Our Stars"),
            Pelicula(1, R.drawable.ficcion1, "Avengers"),
            Pelicula(2, R.drawable.ficcion2, "Frozen"),
            Pelicula(4, R.drawable.ficcion3, "Up"),
            Pelicula(5, R.drawable.ficcion4, "Black Panther"),
            Pelicula(6, R.drawable.ficcion5, "Toy Story"),
            Pelicula(7, R.drawable.ficcion6, "Rápidos y Furiosos"),
            Pelicula(8, R.drawable.ficcion7, "Encanto"),
            Pelicula(9, R.drawable.ficcion8, "Inception"),
            Pelicula(41, R.drawable.terror6, "La Monja"),
            Pelicula(37, R.drawable.terror1, "El Conjuro"),
            Pelicula(38, R.drawable.terror3, "It")
        )

        // 📐 Configurar el RecyclerView como cuadrícula
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = PeliculaAdapter(peliculasTotales)

        // 🧠 Configurar las pestañas de navegación
        configurarTabs()
    }

    // 🧩 Configura las pestañas para navegación de categorías y filtros
    private fun configurarTabs() {
        val tabsHotList = listOf("Top hoy", "Más reciente", "Popularidad", "Recomendadas")
        val filtros = listOf("Todo", "Películas")

        // Añadir pestañas superiores (Hot)
        tabsHotList.forEach {
            tabsHot.addTab(tabsHot.newTab().setText(it))
        }

        // Añadir pestañas inferiores (Filtro general)
        filtros.forEach {
            tabsFiltro.addTab(tabsFiltro.newTab().setText(it))
        }

        // Acciones al seleccionar pestañas de filtros
        tabsFiltro.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                actualizarLista(peliculasTotales) // 🔁 Aquí podrías agregar más lógica
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Acciones al seleccionar pestañas de categorías destacadas
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

    // 🔄 Actualiza las películas mostradas en el RecyclerView
    private fun actualizarLista(lista: List<Pelicula>) {
        recycler.adapter = PeliculaAdapter(lista)
    }
}
