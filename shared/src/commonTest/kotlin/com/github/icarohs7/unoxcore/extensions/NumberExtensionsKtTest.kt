package com.github.icarohs7.unoxcore.extensions

import com.github.icarohs7.unoxcore.utils.shouldEqual
import kotlin.test.Test

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
}