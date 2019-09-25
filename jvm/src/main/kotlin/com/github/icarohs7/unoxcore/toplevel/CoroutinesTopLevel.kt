@file:Suppress("RedundantSuspendModifier")

package com.github.icarohs7.unoxcore.toplevel

import arrow.core.Tuple2
import arrow.core.Tuple3
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import arrow.core.Tuple7
import arrow.core.Tuple8
import arrow.core.Tuple9
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

private typealias FL<A> = Flow<A>

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B> combineTupled(f1: FL<A>, f2: FL<B>): FL<Tuple2<A, B>> {
    return combine(f1, f2) { a, b -> Tuple2(a, b) }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C> combineTupled(f1: FL<A>, f2: FL<B>, f3: FL<C>): FL<Tuple3<A, B, C>> {
    return combine(f1, f2, f3) { a, b, c -> Tuple3(a, b, c) }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C, D> combineTupled(f1: FL<A>, f2: FL<B>, f3: FL<C>, f4: FL<D>): FL<Tuple4<A, B, C, D>> {
    return combine(f1, f2, f3, f4) { a, b, c, d -> Tuple4(a, b, c, d) }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C, D, E> combineTupled(f1: FL<A>, f2: FL<B>, f3: FL<C>, f4: FL<D>, f5: FL<E>): FL<Tuple5<A, B, C, D, E>> {
    return combine(f1, f2, f3, f4, f5) { a, b, c, d, e -> Tuple5(a, b, c, d, e) }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C, D, E, F> combineTupled(
        f1: FL<A>, f2: FL<B>, f3: FL<C>, f4: FL<D>, f5: FL<E>, f6: FL<F>
): FL<Tuple6<A, B, C, D, E, F>> {
    return combine(f1, f2, f3, f4, f5, f6) { args ->
        @Suppress("UNCHECKED_CAST") Tuple6(
                args[0] as A,
                args[1] as B,
                args[2] as C,
                args[3] as D,
                args[4] as E,
                args[5] as F
        )
    }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C, D, E, F, G> combineTupled(
        f1: FL<A>, f2: FL<B>, f3: FL<C>, f4: FL<D>, f5: FL<E>, f6: FL<F>, f7: FL<G>
): FL<Tuple7<A, B, C, D, E, F, G>> {
    return combine(f1, f2, f3, f4, f5, f6, f7) { args ->
        @Suppress("UNCHECKED_CAST") Tuple7(
                args[0] as A,
                args[1] as B,
                args[2] as C,
                args[3] as D,
                args[4] as E,
                args[5] as F,
                args[6] as G
        )
    }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C, D, E, F, G, H> combineTupled(
        f1: FL<A>, f2: FL<B>, f3: FL<C>, f4: FL<D>, f5: FL<E>, f6: FL<F>, f7: FL<G>, f8: FL<H>
): FL<Tuple8<A, B, C, D, E, F, G, H>> {
    return combine(f1, f2, f3, f4, f5, f6, f7, f8) { args ->
        @Suppress("UNCHECKED_CAST") Tuple8(
                args[0] as A,
                args[1] as B,
                args[2] as C,
                args[3] as D,
                args[4] as E,
                args[5] as F,
                args[6] as G,
                args[7] as H
        )
    }
}

/**
 * Combine all emissions on a single flow emmiting
 * a tuple with the latest emission of each used
 * flow
 */
fun <A, B, C, D, E, F, G, H, I> combineTupled(
        f1: FL<A>, f2: FL<B>, f3: FL<C>, f4: FL<D>, f5: FL<E>, f6: FL<F>, f7: FL<G>, f8: FL<H>, f9: FL<I>
): FL<Tuple9<A, B, C, D, E, F, G, H, I>> {
    return combine(f1, f2, f3, f4, f5, f6, f7, f8, f9) { args ->
        @Suppress("UNCHECKED_CAST") (Tuple9(
                args[0] as A,
                args[1] as B,
                args[2] as C,
                args[3] as D,
                args[4] as E,
                args[5] as F,
                args[6] as G,
                args[7] as H,
                args[8] as I
        ))
    }
}