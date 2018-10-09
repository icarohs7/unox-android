package com.github.icarohs7.core.extensions

import androidx.lifecycle.MutableLiveData

/**
 * Post a list to the live data only if the actual stored value is empty or null
 */
fun <T> MutableLiveData<List<T>>.postValueIfEmptyOrNull(value: List<T>) {
    val listInsideLiveData = this.value ?: emptyList()
    if (listInsideLiveData.isEmpty()) this.postValue(value)
}