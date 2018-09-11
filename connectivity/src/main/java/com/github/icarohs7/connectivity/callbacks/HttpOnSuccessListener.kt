package com.github.icarohs7.connectivity.callbacks

@FunctionalInterface
interface HttpOnSuccessListener<T> {
    fun onSuccess(response: T)
}
