package com.github.icarohs7.library.delegates

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> mutableLazy(initializer: () -> T): MutableLazy<T> = MutableLazy(initializer)

/**
 * Implementation of the [Lazy] delegate
 * for mutable properties
 */
class MutableLazy<T>(val init: () -> T) : ReadWriteProperty<Any?, T> {

    private var value: Option<T> = None

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return when (val v = value) {
            None -> {
                value = Some(init())
                init()
            }
            is Some -> v.t
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = Some(value)
    }
}