package com.github.icarohs7.library.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

/**
 * Returns the drawable from the resource ID, if existant, or
 * null if not
 */
fun Int.drawableByResourceId(context: Context): Drawable? {
    return ContextCompat.getDrawable(context, this)
}

/**
 * Returns a color int value from a color resource id
 */
@ColorInt
fun Int.colorById(context: Context): Int {
    return ContextCompat.getColor(context, this)
}

/**
 * Returns a color drawable from a color resource id,
 * or a drawable for the Black color if the id doesn't exist
 */
fun Int.colorDrawableByResourceId(context: Context): Drawable {
    return colorById(context).let(::ColorDrawable)
}