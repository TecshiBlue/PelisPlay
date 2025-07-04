package com.example.pelisplay

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

/**
 * ListaPeliculasActivity muestra películas organizadas en pestañas,
 * permitiendo filtrar por categorías como "Top hoy", "Más reciente", etc.
 */
class ListaPeliculasActivity : BaseActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var tabsHot: TabLayout
    private lateinit var tabsFiltro: TabLayout

    // Lista completa de películas sin filtro
    private var peliculasTotales: List<Pelicula> = emptyList()

    // Listas por categorías para pestañas Hot
    private val listaTopHoy = listOf(
        Pelicula(R.drawable.ficcion1, "Avengers", "Héroes se unen para salvar al mundo de amenazas cósmicas."),
        Pelicula(R.drawable.terror1, "El Conjuro", "Investigadores paranormales enfrentan una entidad maligna."),
        Pelicula(R.drawable.ficcion4, "Black Panther", "Un rey lucha por proteger su nación y su legado."),
        Pelicula(R.drawable.ficcion6, "Rápidos y Furiosos", "Carreras, acción y familia en una saga de velocidad."),
        Pelicula(R.drawable.peli3, "Rincon del Mundo", "Una mujer japonesa vive la guerra con esperanza.")
    )

    private val listaMasReciente = listOf(
        Pelicula(R.drawable.peli6, "Purple Hearts", "Una cantante y un marine se casan por conveniencia, pero nace el amor."),
        Pelicula(R.drawable.ficcion8, "Inception", "Un ladrón roba secretos a través de los sueños."),
        Pelicula(R.drawable.peli4, "Palmer", "Un exconvicto forma un lazo especial con un niño."),
        Pelicula(R.drawable.peli7, "Me Before You", "Una joven cuida a un hombre parapléjico y ambos se transforman."),
        Pelicula(R.drawable.ficcion7, "Encanto", "Una familia mágica enfrenta la pérdida de sus poderes."),
        Pelicula(R.drawable.terror1, "La Monja", "Una monja maldita aterroriza un convento en Rumanía.")
    )

    private val listaPopularidad = listOf(
        Pelicula(R.drawable.ficcion2, "Frozen", "Una princesa con poderes de hielo busca aceptar quién es."),
        Pelicula(R.drawable.ficcion5, "Toy Story", "Los juguetes tienen vida y viven grandes aventuras."),
        Pelicula(R.drawable.ficcion3, "Up", "Un anciano viaja en globo para cumplir una promesa."),
        Pelicula(R.drawable.peli8, "The Fault in Our Stars", "Dos jóvenes con cáncer se enamoran con intensidad."),
        Pelicula(R.drawable.peli2, "El Grand Magasin", "Comedia sobre la vida dentro de una tienda francesa."),
        Pelicula(R.drawable.terror2, "El Conjuro", "Caso sobrenatural investigado por Ed y Lorraine Warren.")
    )

    private val listaRecomendadas = listOf(
        Pelicula(R.drawable.peli1, "Tu Color", "Dos amigos enfrentan la discriminación en Alemania."),
        Pelicula(R.drawable.peli7, "Me Before You", "Una joven alegre transforma la vida de un hombre amargado."),
        Pelicula(R.drawable.peli8, "The Fault in Our Stars", "Historia de amor profunda y trágica entre adolescentes."),
        Pelicula(R.drawable.peli4, "Jocker", "El origen oscuro de uno de los villanos más famosos."),
        Pelicula(R.drawable.peli3, "Rincon del Mundo", "La vida durante la Segunda Guerra desde la mirada femenina."),
        Pelicula(R.drawable.terror3, "It", "Un payaso maldito aterroriza a los niños de Derry.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_peliculas)

        // Vincular vistas
        tabsHot = findViewById(R.id.tabsHot)
        tabsFiltro = findViewById(R.id.tabsFiltro)
        recycler = findViewById(R.id.recyclerPeliculas)

        // Obtener género enviado por intent, si existe (no se usa en esta pantalla)
        val genero = intent.getStringExtra("genero") ?: "Todo"

        // Cargar lista completa de películas
        peliculasTotales = listOf(
            Pelicula(R.drawable.peli1, "Tu Color", "Dos amigos enfrentan la discriminación en Alemania."),
            Pelicula(R.drawable.peli2, "El Grand Magasin", "Comedia sobre la vida dentro de una tienda francesa."),
            Pelicula(R.drawable.peli3, "En este Rincón del Mundo", "La vida de una mujer japonesa durante la guerra."),
            Pelicula(R.drawable.peli4, "Palmer", "Un exconvicto se convierte en figura paterna."),
            Pelicula(R.drawable.peli5, "El Conjuro", "Caso real de una casa embrujada en EE.UU."),
            Pelicula(R.drawable.peli6, "Purple Hearts", "Una historia de amor entre dos mundos opuestos."),
            Pelicula(R.drawable.peli7, "Me Before You", "Una joven cuida a un hombre en silla de ruedas."),
            Pelicula(R.drawable.peli8, "The Fault in Our Stars", "Dos adolescentes enfrentan el amor y la enfermedad."),
            Pelicula(R.drawable.ficcion1, "Avengers", "Héroes de Marvel se unen para vencer al mal."),
            Pelicula(R.drawable.ficcion2, "Frozen", "Una reina del hielo y su hermana enfrentan el destino."),
            Pelicula(R.drawable.ficcion3, "Up", "Una aventura en globo con un niño y un anciano."),
            Pelicula(R.drawable.ficcion4, "Black Panther", "Wakanda lucha por su identidad y su rey."),
            Pelicula(R.drawable.ficcion5, "Toy Story", "Los juguetes cobran vida cuando nadie los ve."),
            Pelicula(R.drawable.ficcion6, "Rápidos y Furiosos", "Acción sin límites sobre ruedas."),
            Pelicula(R.drawable.ficcion7, "Encanto", "Una familia mágica colombiana enfrenta su destino."),
            Pelicula(R.drawable.ficcion8, "Inception", "Sueños dentro de sueños, y una misión peligrosa."),
            Pelicula(R.drawable.terror1, "La Monja", "Una entidad demoníaca aparece en un convento."),
            Pelicula(R.drawable.terror2, "El Conjuro", "Actividad paranormal basada en hechos reales."),
            Pelicula(R.drawable.terror3, "It", "Un payaso malvado se alimenta del miedo de los niños.")
        )

        // Configurar RecyclerView como grid de 3 columnas
        recycler.layoutManager = GridLayoutManager(this, 3)

        // Mostrar todas las películas inicialmente
        recycler.adapter = PeliculaAdapter(peliculasTotales)

        // Configurar comportamiento de las pestañas
        configurarTabs()
    }

    /**
     * Configura los TabLayout para filtrar y cambiar la lista de películas mostradas.
     */
    private fun configurarTabs() {
        // Agregar pestañas Hot (arriba)
        val tabsHotList = listOf("Top hoy", "Más reciente", "Popularidad", "Recomendadas")
        tabsHotList.forEach { tabsHot.addTab(tabsHot.newTab().setText(it)) }

        // Agregar pestañas filtro (abajo)
        val filtros = listOf("Todo", "Películas")
        filtros.forEach { tabsFiltro.addTab(tabsFiltro.newTab().setText(it)) }

        // Evento cuando se selecciona una pestaña de filtro
        tabsFiltro.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Por ahora, mostrar toda la lista sin cambios
                actualizarLista(peliculasTotales)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Evento cuando se selecciona una pestaña Hot
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

    /**
     * Actualiza el RecyclerView con la lista de películas que se pasa como parámetro.
     */
    private fun actualizarLista(lista: List<Pelicula>) {
        recycler.adapter = PeliculaAdapter(lista)
    }
}
