/*
 * MIT License
 *
 * Copyright (c) 2018 Icaro R D Temponi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.icarohs7.animation.extensions

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.view.View
import android.view.ViewPropertyAnimator

fun View.animateTo(duration: Long = 500L, callback: () -> Unit = {}, fn: ViewPropertyAnimator.() -> Unit) {
    buildAnimation(duration, callback) { fn() }
}

fun View.animateScaleIn(duration: Long = 500L, callback: () -> Unit = {}) {
    buildAnimation(duration, callback) {
        scaleX(1f)
        scaleY(1f)
    }
}

fun View.animateScaleOut(duration: Long = 500L, callback: () -> Unit = {}) {
    buildAnimation(duration, callback) {
        scaleX(0f)
        scaleY(0f)
    }
}

fun View.animateToggleScale(show: Boolean, duration: Long = 500L, callback: () -> Unit = {}) {
    if (show) {
        this.animateScaleIn(duration, callback)
    } else {
        this.animateScaleOut(duration, callback)
    }
}

fun View.animateFadeIn(duration: Long = 500L, callback: () -> Unit = {}) {
    buildAnimation(duration, callback) {
        alpha(1f)
    }
}

fun View.animateFadeOut(duration: Long = 500L, callback: () -> Unit = {}) {
    buildAnimation(duration, callback) {
        alpha(0f)
    }
}

fun View.animateToggleFade(show: Boolean, duration: Long = 500L, callback: () -> Unit = {}) {
    if (show) {
        this.animateFadeIn(duration, callback)
    } else {
        this.animateFadeOut(duration, callback)
    }
}


private fun View.buildAnimation(duration: Long, listener: () -> Unit, fn: ViewPropertyAnimator.() -> Unit) {
    this.animate()
            .setDuration(duration)
            .setListener(animatorListener(listener))
            .fn()
}

private fun animatorListener(callback: () -> Unit): AnimatorListener {
    return object : AnimatorListener {
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