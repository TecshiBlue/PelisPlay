package com.example.pelisplay

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

// 🎞️ BannerAdapter: Adaptador para ViewPager2 que muestra banners animados con auto-scroll
class BannerAdapter(
    private val banners: List<Int>,           // Lista de IDs de imágenes (R.drawable.xxx)
    private val viewPager: ViewPager2         // Referencia al ViewPager donde se usa este adapter
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    // 🧠 Handler para programar el auto-scroll de los banners
    private val handler = Handler(Looper.getMainLooper())

    // 🔁 Tarea periódica que cambia automáticamente el banner cada 3 segundos
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val count = itemCount
            if (count > 1) {
                val nextItem = (viewPager.currentItem + 1) % count
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 3000) // Repetir en 3s
            }
        }
    }

    // 🧱 Configuración inicial del ViewPager2 con animaciones y auto-scroll
    init {
        // Iniciar auto-scroll
        handler.postDelayed(autoScrollRunnable, 3000)

        // Configuración visual para efecto de carrusel
        viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            val transformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40)) // Espacio entre banners
                addTransformer { page, position ->
                    val scale = 0.85f + (1 - abs(position)) * 0.15f
                    page.scaleX = scale
                    page.scaleY = scale
                    page.alpha = 0.5f + (1 - abs(position)) * 0.5f
                }
            }
            setPageTransformer(transformer)
        }
    }

    // 👷 Inflar el layout de cada banner (banner_item.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner_item, parent, false)
        return BannerViewHolder(view)
    }

    // 🎨 Asignar imagen al banner y animarla suavemente
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.imageView.setImageResource(banners[position])

        // Animación de entrada suave
        holder.imageView.apply {
            alpha = 0f
            scaleX = 1.1f
            scaleY = 1.1f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(600)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    override fun getItemCount(): Int = banners.size

    // 🧱 ViewHolder: contiene un solo ImageView por banner
    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.bannerImage)
    }
}
