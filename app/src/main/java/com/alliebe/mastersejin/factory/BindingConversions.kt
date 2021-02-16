package com.alliebe.mastersejin.factory

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.alliebe.mastersejin.R
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

@GlideModule
class AppNameGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply {
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).signature(
                ObjectKey(System.currentTimeMillis().toShort())
            )
        }
    }
}

object BindingConversions {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: Int) {
        Glide.with(imageView.context).load(url)
            .error(R.drawable.error)
            .override(320, 240)
            .centerCrop()
            .into(imageView)
    }

}