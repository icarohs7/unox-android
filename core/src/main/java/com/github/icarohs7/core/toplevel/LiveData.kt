package com.github.icarohs7.core.toplevel

import androidx.lifecycle.MutableLiveData

/**
 * Create a mutable live data with an initial value
 */
fun <T> mutableLiveDataOf(initialValue: T): MutableLiveData<T> {
    return MutableLiveData<T>().apply { postValue(initialValue) }
}