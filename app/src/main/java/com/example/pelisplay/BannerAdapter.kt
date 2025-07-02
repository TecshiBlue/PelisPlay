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

class BannerAdapter(
    private val banners: List<Int>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())
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
        handler.postDelayed(autoScrollRunnable, 3000)

        viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer { page, position ->
                val scale = 0.85f + (1 - kotlin.math.abs(position)) * 0.15f
                page.scaleX = scale
                page.scaleY = scale
                page.alpha = 0.5f + (1 - kotlin.math.abs(position)) * 0.5f
            }

            setPageTransformer(transformer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner_item, parent, false) // 🔧 asegurado que usa el parent
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.imageView.setImageResource(banners[position])

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

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.bannerImage)
    }
}
