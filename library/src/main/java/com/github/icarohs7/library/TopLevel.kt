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

@file:JvmName("TopLevel")

package com.github.icarohs7.library

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.library.extensions.hasTheSameDispatcherAs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

/**
 * Execute an operation in the current coroutine context if it's different from the [foregroundContext]
 * or switch to a background context default to [Dispatchers.Default] and execute the operation there,
 * suspending until the operation is done
 *
 * @param backgroundContext The context that will be used if the coroutine is being executed on the foreground
 * @param foregroundContext The context that will be avoided for the execution of the task
 */
suspend fun <T> onBackground(
        backgroundContext: CoroutineDispatcher = Dispatchers.Default,
        foregroundContext: CoroutineDispatcher = UnoxAndroid.foregroundDispatcher,
        block: suspend CoroutineScope.() -> T
): T {
    return when (coroutineContext hasTheSameDispatcherAs foregroundContext) {
        true -> withContext(backgroundContext, block)
        false -> withContext(coroutineContext, block)
    }
}

/**
 * Execute an operation in the current coroutine context if it's the same as the [foregroundContext]
 * or switch to the foreground context default to [Dispatchers.Main] and execute the operation there,
 * suspending until the operation is done
 *
 * @param foregroundContext The context in that the operation will be run
 */
suspend fun <T> onForeground(
        foregroundContext: CoroutineDispatcher = UnoxAndroid.foregroundDispatcher,
        block: suspend CoroutineScope.() -> T
): T {
    return when (coroutineContext hasTheSameDispatcherAs foregroundContext) {
        true -> withContext(coroutineContext, block)
        false -> withContext(foregroundContext, block)
    }
}

/**
 * Create a mutable live data with an initial value
 */
fun <T> mutableLiveDataOf(initialValue: T): MutableLiveData<T> {
    return MutableLiveData<T>().apply { postValue(initialValue) }
}

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