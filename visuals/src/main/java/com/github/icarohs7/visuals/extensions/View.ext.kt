package com.github.icarohs7.visuals.extensions

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

/**
 * Define the background tint resource of the View
 */
var View?.backgroundTintColorResource: Int
    @Deprecated("No Gether", level = DeprecationLevel.ERROR) get() = throw Exception("No gether for tint resource")
    set(value) {
        this?.apply {
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, value))
        }
    }

/**
 * Define the background tint of the View
 */
var View?.backgroundTintColor: Int
    @Deprecated("No Gether", level = DeprecationLevel.ERROR) get() = throw Exception("No gether for tint resource")
    set(value) {
        this?.apply {
            backgroundTintList = ColorStateList.valueOf(value)
        }
    }

/**
 * Resize a view with a new width and height
 */
fun View?.resize(w: Int? = null, h: Int? = null) {
    this?.apply {
        val lp = this.layoutParams
        if (lp != null) {
            lp.width = w ?: lp.width
            lp.height = h ?: lp.height
            this.layoutParams = lp
            this.requestLayout()
            this.invalidate()
        }
    }
}

/**
 * Remove a view from its parent layout
 */
fun View?.removeFromParent() {
    this?.apply {
        val parent: ViewGroup? = this.parent as? ViewGroup?
        parent?.removeView(this)
    }
}