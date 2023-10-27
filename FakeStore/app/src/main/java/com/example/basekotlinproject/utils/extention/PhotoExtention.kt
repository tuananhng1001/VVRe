package com.example.basekotlinproject.utils.extention

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.basekotlinproject.R

fun ImageView.loadImageFromUrlWithLoading(url: String?) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(context)
        .load(url)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_placeholder)
        .into(this)
}

fun ImageView.loadImageFromUrl(url: String?, drawableRes: Int? = null) {
    Glide.with(context)
        .load(url)
        .error(drawableRes)
        .into(this)
}

fun ImageView.loadImageFromDrawable(drawableRes: Int) {
    Glide.with(context)
        .load(drawableRes)
        .error(drawableRes)
        .into(this)
}

fun ImageView.loadCircleImageFromUrl(url: String?, drawableRes: Int) {
    Glide.with(context)
        .load(url)
        .placeholder(drawableRes)
        .error(drawableRes)
        .fitCenter()
        .circleCrop()
        .into(this)
}

fun ImageView.loadPhotoUri(
    uri: Uri?,
    colorInt: Int? = null,
    colorString: String? = null,
    requestListener: RequestListener<Drawable>? = null
) {
    colorInt?.let { background = ColorDrawable(it) }
    colorString?.let { background = ColorDrawable(Color.parseColor(it)) }
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .addListener(requestListener)
        .into(this)
        .clearOnDetach()
}