package com.github.icarohs7.notification.providers.implementations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.notification.providers.abstractions.ObjectNotificationProvider

/**
 * Provider used to provide a observable value of a given type
 */
internal class ObjectNotificationProviderImpl<T> : ObjectNotificationProvider<T> {
    /**
     * Channel that the notification will flow through
     */
    override val channel: LiveData<T> = MutableLiveData<T>()

    /**
     * Emit a notification passing the value
     */
    override fun notify(value: T) {
        (channel as MutableLiveData).postValue(value)
    }
}