package com.github.icarohs7.core.extensions

import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 * Verify if 2 coroutines are being executed by the same dispatcher
 */
infix fun CoroutineContext.hasTheSameDispatcherAs(other: CoroutineContext): Boolean =
        this[ContinuationInterceptor] == other[ContinuationInterceptor] //TODO cover with tests