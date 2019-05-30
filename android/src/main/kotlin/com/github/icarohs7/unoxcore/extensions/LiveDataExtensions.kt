package com.github.icarohs7.unoxcore.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.toPublisher
import io.reactivex.Flowable

/** Retrieve the value of the livedata or return the fallback if null */
fun <T> LiveData<T>.valueOr(fallback: T): T =
        value ?: fallback

/**
 * Kotlin wrapper to simplify the
 * observation of a [LiveData]
 */
fun <T> LiveData<T>.observe(lifecycle: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(lifecycle, Observer<T?> { it?.let(observer) })
}

/**
 * Convert the given [LiveData] to [Flowable]
 */
fun <T> LiveData<T>.toFlowable(lifecycle: LifecycleOwner): Flowable<T> {
    return Flowable.fromPublisher(this.toPublisher(lifecycle))
}