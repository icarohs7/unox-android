@file:JvmName("Helpers")

package com.github.icarohs7.library.delegates

import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.isAccessible

/**
 * Helper extension of a mutable property used to expose it to set its value
 * safely, then hide it again, reverting its visibility to the default value
 */
internal fun <T, R> KMutableProperty<T>.accessibleTransaction(fn: KMutableProperty<T>.() -> R): R {
    val originalAccessibility = isAccessible
    return try {
        isAccessible = true
        fn()
    } finally {
        isAccessible = originalAccessibility
    }
}