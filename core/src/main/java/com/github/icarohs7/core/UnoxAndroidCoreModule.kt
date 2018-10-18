package com.github.icarohs7.core

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.ExecutorCoroutineDispatcher
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import java.util.concurrent.Executor
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Interface used to store the settings of the module
 */
interface UnoxAndroidCoreModule {
    companion object {

        /**
         * Coroutine pool used on the library,
         * when changed, also redefine [SCOPE]
         * and [EXECUTOR] from the library
         */
        var POOL: ExecutorCoroutineDispatcher = newFixedThreadPoolContext(2, "unox")
            set(value) {
                SCOPE = CoroutineScope(value)
                EXECUTOR = value.executor
                field = value
            }

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