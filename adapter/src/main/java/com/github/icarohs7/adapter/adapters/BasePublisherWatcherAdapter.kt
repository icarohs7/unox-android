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

package com.github.icarohs7.adapter.adapters

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * Adapter based on observability and dynamic lists built using [org.reactivestreams.Publisher]
 */
abstract class BasePublisherWatcherAdapter<T, DB : ViewDataBinding>(
        @LayoutRes itemLayout: Int,
        val dataSetObservable: Publisher<List<T>>
) : BaseBindingAdapter<T, DB>(itemLayout) {

    var subscription: Subscription? = null
    open val subscriber: Subscriber<List<T>> = object : Subscriber<List<T>> {
        override fun onComplete() {
        }

        override fun onSubscribe(subs: Subscription?) {
            subscription = subs
        }

        override fun onNext(items: List<T>?) {
            items?.let { nonNullItems ->
                dataSet = nonNullItems
            }
        }

        override fun onError(t: Throwable?) {
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        dataSetObservable.subscribe(subscriber)
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        subscription?.cancel()
        super.onDetachedFromRecyclerView(recyclerView)
    }
}