package com.github.icarohs7.unoxandroid

import timber.log.Timber

/**
 * Tag used by logging
 */
private const val UnoxTAG: String = "UnoxAndroid"
private val timber: Timber.Tree get() = Timber.tag(UnoxTAG)

internal fun logI(value: Any?) {
    when (value) {
        is Throwable -> timber.i(value)
        else -> timber.i("$value")
    }
}