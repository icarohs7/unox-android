package com.github.icarohs7.core

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.ExecutorCoroutineDispatcher
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import java.util.concurrent.Executor
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.math.max

/**
 * Interface used to store the settings of the module
 */
interface UnoxAndroidCoreModule {
    companion object {

        /**
         * Coroutine pool used on the library
         */
        var POOL: ExecutorCoroutineDispatcher =
                newFixedThreadPoolContext(
                        max(2, Runtime.getRuntime().availableProcessors()), "unox")

        /**
         * Context used on all coroutines of the library
         */
        var CONTEXT: CoroutineContext = Job()

        /**
         * Scope used on all coroutines of the library
         */
        var SCOPE: CoroutineScope = CoroutineScope(POOL)

        /**
         * Executor used by the library
         */
        var EXECUTOR: Executor = POOL.executor
    }
}