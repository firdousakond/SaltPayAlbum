package com.firdous.saltpayblank.ui.util

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.firdous.saltpayblank.R


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(AppCompatResources.getDrawable(imageView.context,R.drawable.ic_placeholder))
            .into(imageView)
    }
}

@BindingAdapter("favouriteImage")
fun setFavouriteImage(imageView: ImageView, isFavourite: Boolean){
    if (isFavourite) {
        imageView.setImageResource(R.drawable.ic_favourite_filled)
    } else {
        imageView.setImageResource(R.drawable.ic_favorite)
    }
}