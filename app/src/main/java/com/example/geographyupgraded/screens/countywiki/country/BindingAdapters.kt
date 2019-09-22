package com.example.geographyupgraded.screens.countywiki.country

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.geographyupgraded.R
import com.example.geographyupgraded.network.CountryApiStatus
import com.example.geographyupgraded.screens.countywiki.CountryPresentationModel
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
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

@BindingAdapter("app:hideIfEmpty")
fun hideIfEmpty(view: View, data: LiveData<List<CountryPresentationModel>>) {
    when (data.value == null || data.value?.size == 0) {
        true -> view.visibility = View.GONE
        else -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:hideIfNotEmpty")
fun hideIfNotEmpty(view: ShimmerFrameLayout, data: LiveData<List<CountryPresentationModel>>) {
    if (!data.value.isNullOrEmpty()) {
        view.stopShimmer()
        view.visibility = View.GONE
    }
}

@BindingAdapter("app:handleApiError")
fun handleApiError(view: ShimmerFrameLayout, status: LiveData<CountryApiStatus?>) {
    if (status.value == CountryApiStatus.ERROR) {
        view.visibility = View.VISIBLE
        view.stopShimmer()
        Toast.makeText(view.context, view.context.getString(R.string.api_call_error_message), Toast.LENGTH_LONG).show()
    }
}

