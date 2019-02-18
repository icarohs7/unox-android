package com.github.icarohs7.unoxandroid.extensions

import arrow.core.Tuple2
import arrow.core.Tuple3
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import se.lovef.assert.v1.shouldContain

class RxJavaExtensionsKtTest {
    @Test
    fun `combine 2 flowables`() {
        val f1 = Flowable.just(10)
        val f2 = Flowable.just(20)
        val comb = f1 + f2

        val subs = getSubscriber<Tuple2<Int, Int>>()
        comb.subscribe(subs)

        subs.assertComplete()
        subs.assertValueCount(1)
        subs.events.first() shouldContain Tuple2(10, 20)
    }

    @Test
    fun `combine 3 flowables`() {
        val f1 = Flowable.just("A")
        val f2 = Flowable.just("B")
        val f3 = Flowable.just("C")
        val comb = f1 + f2 + f3

        val subs = getSubscriber<Tuple3<String, String, String>>()
        comb.subscribe(subs)

        subs.assertComplete()
        subs.assertValueCount(1)
        subs.events.first() shouldContain Tuple3("A", "B", "C")
    }

    @Test
    fun `combine 4 flowables`() {
        val f1 = Flowable.just(10L)
        val f2 = Flowable.just(20L)
        val f3 = Flowable.just(30L)
        val f4 = Flowable.just(40L)
        val comb = f1 + f2 + f3 + f4

        val subs = getSubscriber<Tuple4<Long, Long, Long, Long>>()
        comb.subscribe(subs)

        subs.assertComplete()
        subs.assertValueCount(1)
        subs.events.first() shouldContain Tuple4(10L, 20L, 30L, 40L)
    }

    @Test
    fun `combine 5 flowables`() {
        val f1 = Flowable.just(1.60)
        val f2 = Flowable.just(2.70)
        val f3 = Flowable.just(3.80)
        val f4 = Flowable.just(4.90)
        val f5 = Flowable.just(5.00)
        val comb = f1 + f2 + f3 + f4 + f5

        val subs = getSubscriber<Tuple5<Double, Double, Double, Double, Double>>()
        comb.subscribe(subs)

        subs.assertComplete()
        subs.assertValueCount(1)
        subs.events.first() shouldContain Tuple5(1.60, 2.70, 3.80, 4.90, 5.00)
    }

    @Test
    fun `combine 6 flowables`() {
        val f1 = Flowable.just(true)
        val f2 = Flowable.just(false)
        val f3 = Flowable.just(10)
        val f4 = Flowable.just(20)
        val f5 = Flowable.just("Omai wa mou shindeiru!")
        val f6 = Flowable.just("NANI!?")
        val comb = f1 + f2 + f3 + f4 + f5 + f6

        val subs = getSubscriber<Tuple6<Boolean, Boolean, Int, Int, String, String>>()
        comb.subscribe(subs)

        subs.assertComplete()
        subs.assertValueCount(1)
        subs.events.first() shouldContain Tuple6(true, false, 10, 20, "Omai wa mou shindeiru!", "NANI!?")
    }

    private fun <T> getSubscriber(): TestSubscriber<T> {
        return TestSubscriber.create<T>()
    }
}