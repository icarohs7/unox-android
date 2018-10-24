package com.github.icarohs7.core.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.github.icarohs7.core.UnoxAndroidCoreModule
import com.github.icarohs7.core.builders.LifecycleObserverBuilder
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.cancelChildren

/**
 * Observe the lifecycle of an activity and attach the coroutine context
 * to it when running and cancel the running coroutines when paused
 */
fun LifecycleOwner.attachLifecycleToUnoxAndroid(scope: CoroutineScope) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            UnoxAndroidCoreModule.SCOPE = scope
            UnoxAndroidCoreModule.CONTEXT = scope.coroutineContext
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            scope.coroutineContext.cancelChildren()
        }
    })
}

/**
 * Attach an observable to the lifecycle of a given lifecycle owner using a builder DSL
 */
fun <T : LifecycleOwner> T.addLifecycleObserver(fn: LifecycleObserverBuilder<T>.() -> Unit) {
    val builder = LifecycleObserverBuilder<T>()
    builder.fn()
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate(): Unit = builder.create(this@addLifecycleObserver)

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart(): Unit = builder.start(this@addLifecycleObserver)

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume(): Unit = builder.resume(this@addLifecycleObserver)

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause(): Unit = builder.pause(this@addLifecycleObserver)

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop(): Unit = builder.stop(this@addLifecycleObserver)

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(): Unit = builder.destroy(this@addLifecycleObserver)

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny(): Unit = builder.any(this@addLifecycleObserver)
    })
}