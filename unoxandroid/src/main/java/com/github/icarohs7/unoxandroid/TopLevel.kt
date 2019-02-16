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

package com.github.icarohs7.unoxandroid

import android.os.Handler
import android.os.Looper
import arrow.core.Try
import arrow.effects.IO
import com.github.icarohs7.unoxandroid.extensions.coroutines.onBackground

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
 * Invoke the given function that will cause side effects
 * returning its result wrapped in an [IO] instance
 */
fun <A> sideEffect(f: () -> A): IO<A> {
    return Try { f() }.fold({ IO.raiseError(it) }, { IO.just(it) })
}

/**
 * Invoke the given function that will cause side effects
 * on a background coroutine, returning its result wrapped
 * in an [IO] instance
 */
suspend fun <A> sideEffectBg(f: suspend () -> A): IO<A> {
    return onBackground {
        val result = Try { f() }
        result.fold({ IO.raiseError<A>(it) }, { IO.just(it) })
    }
}