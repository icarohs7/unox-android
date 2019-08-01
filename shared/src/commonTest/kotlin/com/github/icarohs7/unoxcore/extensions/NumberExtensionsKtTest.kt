package com.github.icarohs7.unoxcore.extensions

import com.github.icarohs7.unoxcore.utils.shouldEqual
import kotlin.test.Test
import kotlin.test.assertFails

class NumberExtensionsKtTest {
    @Test
    fun return_number_value_or_zero_if_null() {
        val n1: Int? = 15
        val r1: Int = n1.orZero()
        r1 shouldEqual 15

        val n2: Int? = null
        val r2: Int = n2.orZero()
        r2 shouldEqual 0

        val n3: Float? = 15f
        val r3: Float = n3.orZero()
        r3 shouldEqual 15f

        val n4: Float? = null
        val r4: Float = n4.orZero()
        r4 shouldEqual 0f

        val n5: Double? = 15.0
        val r5: Double = n5.orZero()
        r5 shouldEqual 15.0

        val n6: Double? = null
        val r6: Double = n6.orZero()
        r6 shouldEqual 0.0

        val n7: Byte? = 15
        val r7: Byte = n7.orZero()
        r7 shouldEqual 15.toByte()

        val n8: Byte? = null
        val r8: Byte = n8.orZero()
        r8 shouldEqual 0.toByte()

        val n9: Short? = 15
        val r9: Short = n9.orZero()
        r9 shouldEqual 15.toShort()

        val n10: Short? = null
        val r10: Short = n10.orZero()
        r10 shouldEqual 0.toShort()

        val n11: Long? = 15L
        val r11: Long = n11.orZero()
        r11 shouldEqual 15L

        val n12: Long? = null
        val r12: Long = n12.orZero()
        r12 shouldEqual 0L
    }

    @Test
    fun use_fallback_if_number_is_zero_or_less() {
        val n1 = 1532
        val f1 = 20
        n1.ifZeroOrLess(f1) shouldEqual 1532

        val n2 = 0
        val f2 = 30
        n2.ifZeroOrLess(f2) shouldEqual 30

        val n3 = -24
        val f3 = 42
        n3.ifZeroOrLess(f3) shouldEqual 42

        val n4 = 1532
        val f4 = 20
        n4.ifZeroOrLess { f4 } shouldEqual 1532

        val n5 = 0
        val f5 = 30
        n5.ifZeroOrLess { f5 } shouldEqual 30

        val n6 = -24
        val f6 = 42
        n6.ifZeroOrLess { f6 } shouldEqual 42

        val n7 = 0
        val r7 = runCatching { n7.ifZeroOrLess { throw IllegalArgumentException() } }
        assertFails { r7.getOrThrow() }::class shouldEqual IllegalArgumentException()::class

        val n8 = -24
        val r8 = runCatching { n8.ifZeroOrLess { throw IllegalStateException() } }
        assertFails { r8.getOrThrow() }::class shouldEqual IllegalStateException()::class
    }
}