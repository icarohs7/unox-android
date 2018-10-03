package com.github.icarohs7.userinterface.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

/**
 * Returns the drawable from the resource ID, if existant, or
 * null if not
 */
fun Int?.drawableByResourceId(context: Context): Drawable? {
    this?.let { resId ->
        return@drawableByResourceId ContextCompat.getDrawable(context, resId)
    }
    return null
}

/**
 * Returns a color int value from a color resource value
 */
@ColorInt
fun Int?.colorById(context: Context): Int? {
    this?.let {
        return@colorById ContextCompat.getColor(context, it)
    }
    return null
}

/**
 * Returns a color drawable from a color resource value
 */
fun Int?.colorDrawableByResourceId(context: Context): Drawable? {
    this?.let { resId ->
        resId.colorById(context)?.let {
            return@colorDrawableByResourceId ColorDrawable(it)
        }
    }
    return null
}