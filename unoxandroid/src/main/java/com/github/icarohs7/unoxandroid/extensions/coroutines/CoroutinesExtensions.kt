package com.github.icarohs7.unoxandroid.extensions.coroutines

import com.github.icarohs7.unoxandroid.UnoxAndroid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
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
    return when (coroutineContext hasTheSameDispatcherAs foregroundContext) {
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
    return when (coroutineContext hasTheSameDispatcherAs foregroundContext) {
        true -> withContext(coroutineContext, block)
        false -> withContext(foregroundContext, block)
    }
}

/**
 * Coroutine scope launching coroutines on the
 * [Dispatchers.Main] dispatcher
 */
@Suppress("FunctionName")
fun MainScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

/**
 * Cancel the coroutine scope
 */
@Suppress("NOTHING_TO_INLINE")
inline fun CoroutineScope.cancelCoroutineScope() {
    job.cancel()
}

/**
 * The job used by this [CoroutineScope]
 */
inline val CoroutineScope.job: Job
    get() = coroutineContext[Job] ?: error("This coroutine scope doesn't have a job: $this")

/**
 * Verify if 2 coroutines are being executed by the same dispatcher
 */
infix fun CoroutineContext.hasTheSameDispatcherAs(other: CoroutineContext): Boolean =
        this[ContinuationInterceptor] == other[ContinuationInterceptor]

/**
 * Consume each element sent by the channel, suspending when there's
 * none until the first element comes in
 */
suspend inline fun <T> ReceiveChannel<T>.forEach(fn: (T) -> Unit) {
    for (item in this) fn(item)
}
