package dev.damodaran.planets.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("app:imageFromUrl")
fun loadImage(view: ImageView, imageFromUrl: String){
    Glide.with(view.context)
        .load(imageFromUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}