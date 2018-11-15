@file:JvmName("LiveDataDelegate")

package com.github.icarohs7.library.delegates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.reflect.KProperty

//TODO think about some way to get rid of this nullability

/** Delegate to redirect any get operation of the property to the live data */
operator fun <T> LiveData<T>.getValue(thisRef: Any?, property: KProperty<*>): T? = value

/** Delegate to redirect any set operation of the property to the mutable live data */
operator fun <T> MutableLiveData<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T?): Unit =
        postValue(value)
