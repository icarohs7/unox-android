package com.github.icarohs7.userinterface.extensions

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat

/**
 * Define the background tint resource of the View
 */
var View.backgroundTintColorResource: Int
    @Deprecated("No Gether", level = DeprecationLevel.ERROR) get() = throw Exception("No gether for tint resource")
    set(value) {
        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, value))
    }

/**
 * Define the background tint of the View
 */
var View.backgroundTintColor: Int
    @Deprecated("No Gether", level = DeprecationLevel.ERROR) get() = throw Exception("No gether for tint resource")
    set(value) {
        backgroundTintList = ColorStateList.valueOf(value)
    }