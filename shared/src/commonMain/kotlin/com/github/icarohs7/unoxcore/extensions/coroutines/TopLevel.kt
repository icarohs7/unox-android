package com.github.icarohs7.unoxcore.extensions.coroutines

import com.github.icarohs7.unoxcore.UnoxCore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

/**
 * Execute an operation in the current coroutine context if it's different from the [foregroundContext]
 * or switch to a background context default to [UnoxCore.backgroundDispatcher] and execute the operation there,
 * suspending until the operation is done
 *
 * @param backgroundContext The context that will be used if the coroutine is being executed on the foreground
 * @param foregroundContext The context that will be avoided for the execution of the task
 */
suspend fun <T> onBackground(
        backgroundContext: CoroutineDispatcher = UnoxCore.backgroundDispatcher,
        foregroundContext: CoroutineDispatcher = UnoxCore.foregroundDispatcher,
        block: suspend CoroutineScope.() -> T
): T {
    val runningOnUi = coroutineContext.dispatcher == foregroundContext.dispatcher
    return when {
        UnoxCore.forceContextSwitchToBackground -> withContext(backgroundContext, block)
        runningOnUi -> withContext(backgroundContext, block)
        else -> withContext(coroutineContext, block)
    }
}

/**
 * Execute an operation in the current coroutine context if it's the same as the [foregroundContext]
 * or switch to the foreground context default to [UnoxCore.foregroundDispatcher] and execute the
 * operation there, suspending until the operation is done
 *
 * @param foregroundContext The context in that the operation will be run
 */
suspend fun <T> onForeground(
        foregroundContext: CoroutineDispatcher = UnoxCore.foregroundDispatcher,
        block: suspend CoroutineScope.() -> T
): T {
    return when (coroutineContext.dispatcher == foregroundContext.dispatcher) {
        true -> withContext(coroutineContext, block)
        false -> withContext(foregroundContext, block)
    }
}

private typealias Suspend<T> = suspend () -> T

/**
 * Execute both functions in parallel and suspend until
 * both are complete, then returning the result of both
 * wrapped in a [Pair]
 */
suspend fun <A, B> parallelPair(op1: Suspend<A>, op2: suspend () -> B): Pair<A, B> {
    return coroutineScope {
        val a = async { op1() }
        val b = async { op2() }
        Pair(a.await(), b.await())
    }
}

/**
 * Execute the three functions in parallel and suspend until
 * they are complete, then returning the result wrapped in
 * a [Triple]
 */
suspend fun <A, B, C> parallelTriple(op1: Suspend<A>, op2: Suspend<B>, op3: Suspend<C>): Triple<A, B, C> {
    return coroutineScope {
        val a = async { op1() }
        val b = async { op2() }
        val c = async { op3() }
        Triple(a.await(), b.await(), c.await())
    }
}

/**
 * Execute all the given operations in parallel
 * using the coroutine context of the calling
 * coroutine ([kotlin.coroutines.EmptyCoroutineContext])
 */
suspend inline fun <T> parallelRun(vararg operations: suspend () -> T): List<T> {
    return coroutineScope { operations.map { async { it() } } }.awaitAll()
}