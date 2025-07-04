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

// Adaptador personalizado para mostrar banners en ViewPager2 con auto-deslizamiento y animación
class BannerAdapter(
    private val banners: List<Int>, // Lista de IDs de imágenes
    private val viewPager: ViewPager2 // Referencia al ViewPager que lo contiene
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    // Manejador para realizar el auto-scroll
    private val handler = Handler(Looper.getMainLooper())

    // Runnable que cambia el banner cada 3 segundos
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val count = itemCount
            if (count > 1) {
                val nextItem = (viewPager.currentItem + 1) % count
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 3000)
            }
        }
    }

    init {
        // Inicia el auto-scroll
        handler.postDelayed(autoScrollRunnable, 3000)

        // Configuración visual del ViewPager (efecto carrusel)
        viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer { page, position ->
                val scale = 0.85f + (1 - abs(position)) * 0.15f
                page.scaleX = scale
                page.scaleY = scale
                page.alpha = 0.5f + (1 - abs(position)) * 0.5f
            }

            setPageTransformer(transformer)
        }
    }

    // Crea el ViewHolder con el layout del banner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner_item, parent, false)
        return BannerViewHolder(view)
    }

    // Asigna la imagen del banner y anima su aparición
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.imageView.setImageResource(banners[position])

        // Animación de entrada para el banner
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

    // ViewHolder que contiene la imagen del banner
    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.bannerImage)
    }
}
