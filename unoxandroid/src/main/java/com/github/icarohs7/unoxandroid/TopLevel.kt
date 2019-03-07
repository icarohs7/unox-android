package com.github.icarohs7.unoxandroid

import android.os.Handler
import android.os.Looper
import arrow.core.Try
import arrow.effects.IO
import com.github.icarohs7.unoxandroid.extensions.coroutines.onBackground
import kotlinx.coroutines.CoroutineScope


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
    return Try { f() }
            .fold({ IO.raiseError<A>(it) }, { IO.just(it) })
            .also(::logI)
}

/**
 * Invoke the given function that will cause side effects
 * on a background coroutine, returning its result wrapped
 * in an [IO] instance
 */
suspend fun <A> sideEffectBg(f: suspend CoroutineScope.() -> A): IO<A> {
    return onBackground {
        Try { f() }
                .fold({ IO.raiseError<A>(it) }, { IO.just(it) })
                .also(::logI)
    }
}