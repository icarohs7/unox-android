@file:Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE", "FunctionName")

package com.github.icarohs7.unoxandroid.extensions.coroutines

import com.github.icarohs7.unoxandroid.UnoxAndroid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        backgroundContext: CoroutineDispatcher = UnoxAndroid.backgroundDispatcher,
        foregroundContext: CoroutineDispatcher = Dispatchers.Main,
        block: suspend CoroutineScope.() -> T
): T {
    val runningOnUi = coroutineContext.dispatcher == foregroundContext.dispatcher
    return when {
        UnoxAndroid.forceContextSwitchToBackground -> withContext(backgroundContext, block)
        runningOnUi -> withContext(backgroundContext, block)
        else -> withContext(coroutineContext, block)
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
        foregroundContext: CoroutineDispatcher = Dispatchers.Main,
        block: suspend CoroutineScope.() -> T
): T {
    return when (coroutineContext.dispatcher == foregroundContext.dispatcher) {
        true -> withContext(coroutineContext, block)
        false -> withContext(foregroundContext, block)
    }
}