package com.github.icarohs7.library.delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.isAccessible

fun <T> redirectToProperty(
        vararg targetProperty: KMutableProperty<T>
): PropertyRedirectionDelegate<T> = PropertyRedirectionDelegate(*targetProperty)

fun <T> redirectToProperty(
        fn: () -> Array<out KMutableProperty<T>>
): PropertyRedirectionDelegate<T> = PropertyRedirectionDelegate(fn)

/**
 * Delegate used to redirect the get and set actions on a property to the same
 * action on another property
 */
class PropertyRedirectionDelegate<T>(
        private vararg val targetProperty: KMutableProperty<T>
) : ReadWriteProperty<Any, T> {
    constructor(fn: () -> Array<out KMutableProperty<T>>) : this(*fn())

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return targetProperty[0].accessibleTransaction { call() }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        targetProperty.forEach { prop -> prop.accessibleTransaction { setter.call(value) } }
    }

    private fun <T, R> KMutableProperty<T>.accessibleTransaction(fn: KMutableProperty<T>.() -> R): R {
        val originalAccessibility = isAccessible
        return try {
            isAccessible = true
            fn()
        } finally {
            isAccessible = originalAccessibility
        }
    }
}