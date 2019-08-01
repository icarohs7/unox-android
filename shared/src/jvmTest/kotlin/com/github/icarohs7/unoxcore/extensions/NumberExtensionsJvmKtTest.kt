package com.github.icarohs7.unoxcore.extensions

import com.github.icarohs7.unoxcore.utils.shouldEqual
import org.junit.Test
import se.lovef.assert.v1.throws

class NumberExtensionsJvmKtTest {
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
        ;{ r7.getOrThrow() } throws IllegalArgumentException::class

        val n8 = -24
        val r8 = runCatching { n8.ifZeroOrLess { throw NegativeArraySizeException() } }
        ;{ r8.getOrThrow() } throws NegativeArraySizeException::class
    }
}