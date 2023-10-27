package com.example.basekotlinproject.utils.extention

import android.content.res.Resources
import kotlin.math.roundToInt

fun String.pxToDp(): Int {
    return (this.toInt() / Resources.getSystem().displayMetrics.density).roundToInt()
}

fun String.dpToPx(): Int {
    return (this.toInt() * Resources.getSystem().displayMetrics.density).roundToInt()
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).roundToInt()
}


