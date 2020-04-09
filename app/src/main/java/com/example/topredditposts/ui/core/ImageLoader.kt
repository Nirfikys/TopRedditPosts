package com.example.topredditposts.ui.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.topredditposts.R
import com.example.topredditposts.presentation.core.RX
import com.example.topredditposts.remote.core.Request

object ImageLoader {

    private val imageMap: HashMap<String, Bitmap> = HashMap()

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImage(imageView: ImageView, url: String) {
        RX(
            {
                if (imageMap.containsKey(url))
                    imageMap[url]!!
                else
                    Request.getBinary(url) { BitmapFactory.decodeStream(it) }
            },
            {
                if (!imageMap.containsKey(url))
                    imageMap[url] = it
                imageView.setImageBitmap(it)
            },
            {
                val context = imageView.context
                imageView.setImageResource(R.drawable.ic_error_black_48dp)
            }
        )
    }
}