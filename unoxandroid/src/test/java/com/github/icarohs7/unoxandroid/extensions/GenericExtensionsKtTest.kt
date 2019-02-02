package com.github.icarohs7.unoxandroid.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class GenericExtensionsKtTest {
    @Test
    fun `get the value of a nullable variable or a fallback`() {
        val v1: Int? = 20
        val r1: Int = v1.valueOr(10)
        r1 shouldEqual 20

        val v2: Int? = null
        val r2: Int = v2.valueOr(1532)
        r2 shouldEqual 1532

        val n3: String? = "ORAORAORAORAORAORAORAORA!!!"
        val r3: String = n3.valueOr("Is that a JoJo reference!?")
        r3 shouldEqual "ORAORAORAORAORAORAORAORA!!!"

        val n4: String? = null
        val r4: String = n4.valueOr("MUDAMUDAMUDAMUDAMUDA!!!")
        r4 shouldEqual "MUDAMUDAMUDAMUDAMUDA!!!"
    }
}