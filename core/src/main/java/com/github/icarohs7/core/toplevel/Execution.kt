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

package com.github.icarohs7.core.toplevel

import com.github.icarohs7.core.settings.UnoxAndroidSettings
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

/**
 * Coroutine pool used on the library
 */
val NXBGPOOL by lazy { newFixedThreadPoolContext(UnoxAndroidSettings.NXBGPOOL_NUMBER_OF_THREADS, "unox") }

/**
 * Run a function on the UI Thread after some time
 */
fun runAfterDelay(delayTime: Int, fn: suspend (CoroutineScope) -> Unit) {
    onBgNoReturn {
        delay(delayTime.toLong())
        onUi(fn)
    }
}

/**
 * Run a function on the UI Thread
 */
fun onUi(fn: suspend (CoroutineScope) -> Unit) = CoroutineScope(Dispatchers.Main).launch {
    fn(this)
}

/**
 * Run a function on the background coroutine context and
 * returns the deferred
 */
fun <T> onBg(fn: suspend (CoroutineScope) -> T) = CoroutineScope(NXBGPOOL).async {
    fn(this)
}

/**
 * Run a function on the background coroutine context and
 * returns the job, use for fire and forget operations
 */
fun onBgNoReturn(fn: suspend (CoroutineScope) -> Unit) = CoroutineScope(NXBGPOOL).launch {
    fn(this)
}

/**
 * Run a function and ignore the returning value,
 * instead returning Unit
 */
inline fun noReturn(fn: () -> Unit) =
        fn()

/**
 * Run a suspend function and ignore the returning
 * value, instead returning Unit
 */
@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend inline fun noReturnSusp(fn: suspend () -> Unit) =
        fn()