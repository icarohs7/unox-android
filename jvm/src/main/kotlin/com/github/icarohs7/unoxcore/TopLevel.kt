package com.github.icarohs7.unoxcore

import arrow.core.Try
import com.github.icarohs7.unoxcore.extensions.coroutines.onBackground
import io.reactivex.Single
import io.reactivex.SingleEmitter
import kotlinx.coroutines.CoroutineScope
import java.io.Closeable

/**
 * Create a [Single] using the given configuration
 * lambda
 */
@Suppress("FunctionName")
fun <T> Single(config: SingleEmitter<T>.() -> Unit): Single<T> {
    return Single.create(config)
}

/**
 * Execute the given block using the resource
 * parameterized, then close the resource after
 * the execution is done
 */
inline fun <A : Closeable> useRes(res1: A, block: (A) -> Unit) {
    res1.use(block)
}

/**
 * Execute the given block using the resources
 * parameterized, then close them after
 * the execution is done
 */
inline fun <A : Closeable, B : Closeable> useRes(res1: A, res2: B, block: (A, B) -> Unit) {
    res1.use { r1 ->
        res2.use { r2 ->
            block(r1, r2)
        }
    }
}

/**
 * Execute the given block using the resources
 * parameterized, then close them after
 * the execution is done
 */
inline fun <A : Closeable, B : Closeable, C : Closeable> useRes(
        res1: A,
        res2: B,
        res3: C,
        block: (A, B, C) -> Unit
) {
    res1.use { r1 ->
        res2.use { r2 ->
            res3.use { r3 ->
                block(r1, r2, r3)
            }
        }
    }
}

/**
 * Execute the given block using the resources
 * parameterized, then close them after
 * the execution is done
 */
inline fun <A : Closeable, B : Closeable, C : Closeable, D : Closeable> useRes(
        res1: A,
        res2: B,
        res3: C,
        res4: D,
        block: (A, B, C, D) -> Unit
) {
    res1.use { r1 ->
        res2.use { r2 ->
            res3.use { r3 ->
                res4.use { r4 ->
                    block(r1, r2, r3, r4)
                }
            }
        }
    }
}

/**
 * Execute the given block using the resources
 * parameterized, then close them after
 * the execution is done
 */
inline fun <A : Closeable, B : Closeable, C : Closeable, D : Closeable, E : Closeable> useRes(
        res1: A,
        res2: B,
        res3: C,
        res4: D,
        res5: E,
        block: (A, B, C, D, E) -> Unit
) {
    res1.use { r1 ->
        res2.use { r2 ->
            res3.use { r3 ->
                res4.use { r4 ->
                    res5.use { r5 ->
                        block(r1, r2, r3, r4, r5)
                    }
                }
            }
        }
    }
}

/**
 * Run the given block on a background coroutine, and return
 * its result wrapped on a [Try] instance
 */
suspend fun <A> tryBg(f: suspend CoroutineScope.() -> A): Try<A> {
    return onBackground { Try { f() }.also(UnoxCore.logger) }
}