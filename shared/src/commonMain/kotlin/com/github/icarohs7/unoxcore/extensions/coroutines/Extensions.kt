package com.github.icarohs7.unoxcore.extensions.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


/**
 * Cancel the coroutine scope
 */
fun CoroutineScope.cancelCoroutineScope(): Unit = this.cancel()

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
 * Add the current Job as a child
 * of the given [parent] scope,
 * cancelling it when the parent
 * completes
 */
fun Job.addTo(parent: CoroutineScope) {
    parent.job.invokeOnCompletion { this.cancel() }
}

/**
 * Consume each element sent by the channel, suspending when there's
 * none until the first element comes in
 */
suspend inline fun <T> ReceiveChannel<T>.forEach(fn: (T) -> Unit) {
    for (item in this) fn(item)
}

/**
 * [Iterable.map] function executing
 * each operation in parallel and then
 * joining the result and returning it
 */
suspend fun <A, B> Iterable<A>.parallelMap(f: suspend (A) -> B): List<B> {
    return coroutineScope {
        map { async { f(it) } }.awaitAll()
    }
}

/**
 * Map the flow to the mapping of
 * the emitted list using the given
 * transformer
 */
fun <T, R> Flow<Iterable<T>>.innerMap(transform: (T) -> R): Flow<List<R>> {
    return this.map { it.map(transform) }
}

/**
 * [Iterable.filter] function executing
 * each operation in parallel and then
 * joining the resulting elements market
 * to be kept and returning the new filtered
 * collection
 */
suspend fun <A : Any> Iterable<A>.parallelFilter(predicate: suspend (A) -> Boolean): List<A> {
    return coroutineScope {
        parallelMap { if (predicate(it)) it else null }.filterNotNull()
    }
}

/**
 * Map the flow to the filteing of
 * the emitted list using the given
 * predicate
 */
fun <T> Flow<Iterable<T>>.innerFilter(predicate: (T) -> Boolean): Flow<List<T>> {
    return this.map { it.filter(predicate) }
}

/**
 * [let] being executed on another coroutine context.
 * Credits to [pablisco](https://github.com/pablisco) for the
 * [implementation](https://gist.github.com/pablisco/f83f98e427acb55435a02880f1efbff2)
 */
suspend fun <T, R> T.letOn(
        context: CoroutineContext,
        block: suspend CoroutineScope.(T) -> R
): R = withContext(context) { block(this@letOn) }

/**
 * [also] being executed on another coroutine context.
 * Credits to [pablisco](https://github.com/pablisco) for the
 * [implementation](https://gist.github.com/pablisco/f83f98e427acb55435a02880f1efbff2)
 */
suspend fun <T> T.alsoOn(
        context: CoroutineContext,
        block: suspend CoroutineScope.(T) -> Unit
): T = also { withContext(context) { block(this@alsoOn) } }

/**
 * [apply] being executed on another coroutine context.
 * Credits to [pablisco](https://github.com/pablisco) for the
 * [implementation](https://gist.github.com/pablisco/f83f98e427acb55435a02880f1efbff2)
 */
suspend fun <T> T.applyOn(
        context: CoroutineContext,
        block: suspend T.(CoroutineScope) -> Unit
): T = apply { withContext(context) { this@applyOn.block(this) } }

/**
 * [run] being executed on another coroutine context.
 * Credits to [pablisco](https://github.com/pablisco) for the
 * [implementation](https://gist.github.com/pablisco/f83f98e427acb55435a02880f1efbff2)
 */
suspend fun <T, R> T.runOn(
        context: CoroutineContext,
        block: suspend T.(CoroutineScope) -> R
): R = withContext(context) { this@runOn.block(this) }