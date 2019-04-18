@file:JvmName("TopLevelKtAndroid")

package com.github.icarohs7.unoxcore

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import androidx.annotation.ColorInt

/**
 * Execute the block right away if on main thread, or schedule it
 * to be executed on the main thread otherwise
 */
fun mustRunOnMainThread(fn: () -> Unit) {
    val mainLooper = Looper.getMainLooper()
    val isOnMainLooper = (Looper.myLooper() == mainLooper)

    if (isOnMainLooper) fn()
    else Handler(mainLooper).post(fn)
}

/**
 * @return A randomly generated color
 */
@ColorInt
fun randomColor(): Int {
    val nextInt = { (0..255).random() }
    return Color.rgb(nextInt(), nextInt(), nextInt())
}