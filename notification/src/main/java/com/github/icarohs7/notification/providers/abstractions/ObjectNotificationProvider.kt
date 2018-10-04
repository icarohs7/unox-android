package com.github.icarohs7.notification.providers.abstractions

import androidx.lifecycle.LiveData

interface ObjectNotificationProvider<T> {
    val channel: LiveData<T>
    fun notify(value: T)
}