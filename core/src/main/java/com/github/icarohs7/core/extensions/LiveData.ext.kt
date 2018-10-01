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

package com.github.icarohs7.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * Execute a transaction with the value of a LiveData
 */
fun <T, M : LiveData<T>> M.valueTransaction(failOnNullValue: Boolean = false, fn: T.() -> Unit) {
    if (this.value == null) {
        when (failOnNullValue) {
            true -> throw IllegalStateException("Live data value must not be null")
            false -> return
        }
    }

    this.value!!.fn()
}

/**
 * Observe a LiveData ignoring null values,
 */
fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer { it?.let(observer) })
}

/**
 * Forever observe a LiveData ignoring null values,
 */
fun <T> LiveData<T>.nonNullObserveForever(observer: (t: T) -> Unit) {
    this.observeForever { it?.let(observer) }
}

/**
 * Merges 2 [LiveData] objects and emit notifications when any of
 * them change
 */
infix fun <T> LiveData<T>.mergedWith(other: LiveData<T>): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this, mediator::postValue)
    mediator.addSource(other, mediator::postValue)
    return mediator
}