package com.github.icarohs7.core.builders

import androidx.lifecycle.LifecycleOwner

class LifecycleObserverBuilder<T : LifecycleOwner> {
    internal var create: T.() -> Unit = {}
    internal var start: T.() -> Unit = {}
    internal var resume: T.() -> Unit = {}
    internal var pause: T.() -> Unit = {}
    internal var stop: T.() -> Unit = {}
    internal var destroy: T.() -> Unit = {}
    internal var any: T.() -> Unit = {}

    fun onCreate(fn: T.() -> Unit) {
        create = fn
    }

    fun onStart(fn: T.() -> Unit) {
        start = fn
    }

    fun onResume(fn: T.() -> Unit) {
        resume = fn
    }

    fun onPause(fn: T.() -> Unit) {
        pause = fn
    }

    fun onStop(fn: T.() -> Unit) {
        stop = fn
    }

    fun onDestroy(fn: T.() -> Unit) {
        destroy = fn
    }

    fun onAny(fn: T.() -> Unit) {
        any = fn
    }
}