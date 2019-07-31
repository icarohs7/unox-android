package com.github.icarohs7.unoxcore.extensions.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * Expose the given [Channel] through
 * the [Flow] API, thus creating a
 * "hot flow"
 */
fun <T> Channel<T>.asFlow(capacity: Int = 1, start: CoroutineStart = CoroutineStart.LAZY): Flow<T> {
    return broadcast(capacity, start).asFlow()
}