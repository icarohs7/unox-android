package com.github.icarohs7.core.extensions

import kotlinx.coroutines.experimental.channels.ReceiveChannel

/**
 * Consume each element sent by the channel, suspending when there's
 * none until the first element comes in
 */
suspend inline fun <T> ReceiveChannel<T>.forEach(fn: (T) -> Unit) {
    for (item in this) fn(item)
}