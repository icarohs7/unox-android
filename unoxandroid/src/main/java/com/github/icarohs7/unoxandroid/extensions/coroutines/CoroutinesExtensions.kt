@file:Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE", "FunctionName")

package com.github.icarohs7.unoxandroid.extensions.coroutines

import com.github.icarohs7.unoxandroid.UnoxAndroid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.withContext
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

/**
 * Execute an operation in the current coroutine context if it's different from the [foregroundContext]
 * or switch to a background context default to [Dispatchers.Default] and execute the operation there,
 * suspending until the operation is done
 *
 * @param backgroundContext The context that will be used if the coroutine is being executed on the foreground
 * @param foregroundContext The context that will be avoided for the execution of the task
 */
suspend fun <T> onBackground(
        backgroundContext: CoroutineDispatcher = Dispatchers.Default,
        foregroundContext: CoroutineDispatcher = UnoxAndroid.foregroundDispatcher,
        block: suspend CoroutineScope.() -> T
): T {
    return when (coroutineContext.dispatcher == foregroundContext.dispatcher) {
        true -> withContext(backgroundContext, block)
        false -> withContext(coroutineContext, block)
    }
}

/**
 * Execute an operation in the current coroutine context if it's the same as the [foregroundContext]
 * or switch to the foreground context default to [Dispatchers.Main] and execute the operation there,
 * suspending until the operation is done
 *
 * @param foregroundContext The context in that the operation will be run
 */
suspend fun <T> onForeground(
        foregroundContext: CoroutineDispatcher = UnoxAndroid.foregroundDispatcher,
        block: suspend CoroutineScope.() -> T
): T {
    return when (coroutineContext.dispatcher == foregroundContext.dispatcher) {
        true -> withContext(coroutineContext, block)
        false -> withContext(foregroundContext, block)
    }
}

/**
 * Cancel the coroutine scope
 */
inline fun CoroutineScope.cancelCoroutineScope(): Unit = this.cancel()

/**
 * The job used by this [CoroutineScope]
 */
inline val CoroutineScope.job: Job
    get() = coroutineContext[Job] ?: error("This coroutine scope doesn't have a job: $this")

/**
 * Default [CoroutineDispatcher] used in the scope
 */
inline val CoroutineScope.dispatcher: CoroutineDispatcher
    get() = coroutineContext.dispatcher

/**
 * [CoroutineDispatcher] in which the coroutine is being executed
 */
inline val CoroutineContext.dispatcher: CoroutineDispatcher
    get() = this[ContinuationInterceptor] as? CoroutineDispatcher ?: error("Coroutine dispatcher not found")


/**
 * Consume each element sent by the channel, suspending when there's
 * none until the first element comes in
 */
suspend inline fun <T> ReceiveChannel<T>.forEach(fn: (T) -> Unit) {
    for (item in this) fn(item)
}
