package com.github.icarohs7.visuals.extensions

import android.animation.Animator
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.core.content.ContextCompat

/**
 * Define the background tint resource of the View
 */
var View?.backgroundTintColorResource: Int
    @Deprecated("No Gether", level = DeprecationLevel.ERROR) get() = throw Exception("No gether for tint resource")
    set(value) {
        this ?: return
        backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, value))
    }

/**
 * Define the background tint of the View
 */
var View?.backgroundTintColor: Int
    @Deprecated("No Gether", level = DeprecationLevel.ERROR) get() = throw Exception("No gether for tint resource")
    set(value) {
        this ?: return
        backgroundTintList = ColorStateList.valueOf(value)
    }

/**
 * Resize a view with a new width and height
 */
fun View?.resize(w: Int? = null, h: Int? = null) {
    this ?: return
    val lp = this.layoutParams
    if (lp != null) {
        lp.width = w ?: lp.width
        lp.height = h ?: lp.height
        this.layoutParams = lp
        this.requestLayout()
        this.invalidate()
    }
}

/**
 * Remove a view from its parent layout
 */
fun View?.removeFromParent() {
    this ?: return
    val parent: ViewGroup? = this.parent as? ViewGroup?
    parent?.removeView(this)
}

/**
 * Animate a view with a custom animation, duration and onEnding callback
 */
fun View?.animateTo(duration: Long = 500L, callback: () -> Unit = {}, fn: ViewPropertyAnimator.() -> Unit) {
    this ?: return
    buildAnimation(duration, callback) { fn() }
}

/**
 * Animate a view with a scale in animation and a customizable duration
 * and onEnding callback
 */
fun View?.animateScaleIn(duration: Long = 500L, callback: () -> Unit = {}) {
    this ?: return
    buildAnimation(duration, callback) {
        scaleX(1f)
        scaleY(1f)
    }
}

/**
 * Animate a view with a scale out animation and a customizable duration
 * and onEnding callback
 */
fun View?.animateScaleOut(duration: Long = 500L, callback: () -> Unit = {}) {
    this ?: return
    buildAnimation(duration, callback) {
        scaleX(0f)
        scaleY(0f)
    }
}

/**
 * Toggles the scale of the view according to the first parameter,
 * when true it will [animateScaleIn], else will [animateScaleOut]
 */
fun View?.animateToggleScale(show: Boolean, duration: Long = 500L, callback: () -> Unit = {}) {
    this ?: return
    if (show) {
        this.animateScaleIn(duration, callback)
    } else {
        this.animateScaleOut(duration, callback)
    }
}

/**
 * Animate a view with a fade in animation and a customizable duration
 * and onEnding callback
 */
fun View?.animateFadeIn(duration: Long = 500L, callback: () -> Unit = {}) {
    this ?: return
    buildAnimation(duration, callback) {
        alpha(1f)
    }
}

/**
 * Animate a view with a fade out animation and a customizable duration
 * and onEnding callback
 */
fun View?.animateFadeOut(duration: Long = 500L, callback: () -> Unit = {}) {
    this ?: return
    buildAnimation(duration, callback) {
        alpha(0f)
    }
}

/**
 * Toggles the fading of the view according to the first parameter,
 * when true it will [animateFadeIn], else will [animateFadeOut]
 */
fun View?.animateToggleFade(show: Boolean, duration: Long = 500L, callback: () -> Unit = {}) {
    this ?: return
    if (show) {
        this.animateFadeIn(duration, callback)
    } else {
        this.animateFadeOut(duration, callback)
    }
}


private fun View?.buildAnimation(duration: Long, listener: () -> Unit, fn: ViewPropertyAnimator.() -> Unit) {
    this ?: return
    this.animate()
            .setDuration(duration)
            .setListener(animatorListener(listener))
            .fn()
}

/**
 * Base implementation of the animatorListener, ignoring all
 * callbacks except onAnimationEnd
 */
private fun animatorListener(callback: () -> Unit): Animator.AnimatorListener {
    return object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            callback()
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }
    }
}