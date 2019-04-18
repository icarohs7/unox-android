package com.github.icarohs7.unoxcore.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual
import java.util.Locale

class NumberExtensionsJvmKtTest {
    @Test
    fun should_convert_double_number_to_currency_form() {
        Locale.setDefault(Locale.US)
        val n1 = 15.32
        val c1 = "$15.32"
        n1.asCurrency() shouldEqual c1

        Locale.setDefault(Locale("pt", "BR"))
        val n2 = 15.32
        val c2 = "R$ 15,32"
        n2.asCurrency() shouldEqual c2

        Locale.setDefault(Locale.UK)
        val n3 = 15.32
        val c3 = "£15.32"
        n3.asCurrency() shouldEqual c3

        Locale.setDefault(Locale.ITALY)
        val n4 = 15.32
        val c4 = "€ 15,32"
        n4.asCurrency() shouldEqual c4

        Locale.setDefault(Locale.JAPAN)
        val n5 = 15.32
        val c5 = "￥15"
        n5.asCurrency() shouldEqual c5
    }
}