package com.github.icarohs7.core.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.github.icarohs7.core.UnoxAndroidCoreModule
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