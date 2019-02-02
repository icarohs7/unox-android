package com.github.icarohs7.unoxandroid.extensions

import androidx.lifecycle.LiveData

/** Retrieve the value of the livedata or return the fallback if null */
fun <T> LiveData<T>.valueOr(fallback: T): T =
        value ?: fallback