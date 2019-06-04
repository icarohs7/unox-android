package com.github.icarohs7.unoxcore

import arrow.core.Try
import arrow.effects.IO
import com.github.icarohs7.unoxcore.extensions.orThrow
import io.reactivex.subscribers.TestSubscriber
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import se.lovef.assert.v1.shouldBe
import se.lovef.assert.v1.shouldBeFalse
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.throws
import java.io.Closeable

class TopLevelKtTest {
    @Test
    fun should_invoke_side_effect_functions() {
        var v1 = 0
        val r1 = sideEffect { v1 = 10; v1 }
        v1 shouldEqual 10
        r1 shouldEqual IO.just(10)

        val r2 = sideEffect { throw RuntimeException() }
        ;{ r2.unsafeRunSync() } throws RuntimeException::class
    }

    @Test
    fun should_invoke_side_effect_functions_on_background_coroutine() {
        runBlocking {
            var v1 = 0
            val r1 = sideEffectBg { v1 = 10; v1 }
            v1 shouldEqual 10
            r1 shouldEqual IO.just(10)

            val r2 = sideEffectBg { throw IllegalArgumentException() }
            ;{ r2.unsafeRunSync() } throws IllegalArgumentException::class
        }
    }

    @Test
    fun should_create_rx_java_single() {
        val subscriber = TestSubscriber.create<Int>()
        val single1 = Single<Int> {
            onSuccess(10)
        }

        single1.toFlowable().subscribe(subscriber)

        val emissions = subscriber.events.first()
        emissions shouldEqual listOf(10)
        subscriber.assertComplete()
        subscriber.assertValueCount(1)

        val single2 = Single<Int> {
            onError(NumberFormatException("NANI!?"))
        }

        var errored = false
        single2.toFlowable().subscribe({}, { errored = true })
        runBlocking { delay(400) }
        errored.shouldBeTrue()
        subscriber.dispose()
    }

    class SomeCloseable : Closeable {
        var isClosed = false
            private set

        override fun close() {
            isClosed = true
        }
    }

    @Test
    fun should_use_resource_and_auto_close_1() {
        //Given
        val obj1 = SomeCloseable()
        obj1.isClosed.shouldBeFalse()
        //When
        var num = 0
        useRes(obj1) { a ->
            a shouldBe obj1
            num = 1532
        }
        //Then
        obj1.isClosed.shouldBeTrue()
        num shouldEqual 1532
    }

    @Test
    fun should_use_resource_and_auto_close_2() {
        //Given
        val obj1 = SomeCloseable()
        val obj2 = SomeCloseable()
        obj1.isClosed.shouldBeFalse()
        obj2.isClosed.shouldBeFalse()
        //When
        var num = 0
        useRes(obj1, obj2) { a, b ->
            a shouldBe obj1
            b shouldBe obj2
            num = 1532
        }
        //Then
        obj1.isClosed.shouldBeTrue()
        obj2.isClosed.shouldBeTrue()
        num shouldEqual 1532
    }

    @Test
    fun should_use_resource_and_auto_close_3() {
        //Given
        val obj1 = SomeCloseable()
        val obj2 = SomeCloseable()
        val obj3 = SomeCloseable()
        obj1.isClosed.shouldBeFalse()
        obj2.isClosed.shouldBeFalse()
        obj3.isClosed.shouldBeFalse()
        //When
        var num = 0
        useRes(obj1, obj2, obj3) { a, b, c ->
            a shouldBe obj1
            b shouldBe obj2
            c shouldBe obj3
            num = 1532
        }
        //Then
        obj1.isClosed.shouldBeTrue()
        obj2.isClosed.shouldBeTrue()
        obj3.isClosed.shouldBeTrue()
        num shouldEqual 1532
    }

    @Test
    fun should_use_resource_and_auto_close_4() {
        //Given
        val obj1 = SomeCloseable()
        val obj2 = SomeCloseable()
        val obj3 = SomeCloseable()
        val obj4 = SomeCloseable()
        obj1.isClosed.shouldBeFalse()
        obj2.isClosed.shouldBeFalse()
        obj3.isClosed.shouldBeFalse()
        obj4.isClosed.shouldBeFalse()
        //When
        var num = 0
        useRes(obj1, obj2, obj3, obj4) { a, b, c, d ->
            a shouldBe obj1
            b shouldBe obj2
            c shouldBe obj3
            d shouldBe obj4
            num = 1532
        }
        //Then
        obj1.isClosed.shouldBeTrue()
        obj2.isClosed.shouldBeTrue()
        obj3.isClosed.shouldBeTrue()
        obj4.isClosed.shouldBeTrue()
        num shouldEqual 1532
    }

    @Test
    fun should_use_resource_and_auto_close_5() {
        //Given
        val obj1 = SomeCloseable()
        val obj2 = SomeCloseable()
        val obj3 = SomeCloseable()
        val obj4 = SomeCloseable()
        val obj5 = SomeCloseable()
        obj1.isClosed.shouldBeFalse()
        obj2.isClosed.shouldBeFalse()
        obj3.isClosed.shouldBeFalse()
        obj4.isClosed.shouldBeFalse()
        obj5.isClosed.shouldBeFalse()
        //When
        var num = 0
        useRes(obj1, obj2, obj3, obj4, obj5) { a, b, c, d, e ->
            a shouldBe obj1
            b shouldBe obj2
            c shouldBe obj3
            d shouldBe obj4
            e shouldBe obj5
            num = 1532
        }
        //Then
        obj1.isClosed.shouldBeTrue()
        obj2.isClosed.shouldBeTrue()
        obj3.isClosed.shouldBeTrue()
        obj4.isClosed.shouldBeTrue()
        obj5.isClosed.shouldBeTrue()
        num shouldEqual 1532
    }

    @Test
    fun should_wrap_execution_of_background_code() {
        runBlocking {
            var v1 = 0
            val r1 = tryBg { v1 = 10; v1 }
            v1 shouldEqual 10
            r1 shouldEqual Try.just(10)

            val r2 = tryBg { throw IllegalArgumentException() }
            ;{ r2.orThrow() } throws IllegalArgumentException::class
        }
    }
}