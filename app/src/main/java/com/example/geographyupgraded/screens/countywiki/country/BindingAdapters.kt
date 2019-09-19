package com.example.geographyupgraded.screens.countywiki.country

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView : ImageView, imgUrl: String?){
    imgUrl?.let{
        val imgUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
    }
}

@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number: Float) {
    view.visibility = if (number.compareTo(0) == 0) View.GONE else View.VISIBLE
}