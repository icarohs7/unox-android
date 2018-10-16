package com.github.icarohs7.core.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class StringExtTest {
    @Test
    fun `should strip all non-numeric values from a string`() {
        val s1 = "bcd84.21"
        val exp1 = "84.21"

        s1.onlyNumbers() shouldEqual exp1

        val s2 = "what!"
        val exp2 = ""

        s2.onlyNumbers() shouldEqual exp2

        val s3 = "bcd84.21"
        val exp3 = "8421"

        s3.onlyNumbers(true) shouldEqual exp3

        val s4 = ".32"
        val exp4 = "0.32"

        s4.onlyNumbers() shouldEqual exp4

        val s5 = "aa.aa"
        val exp5 = ""

        s5.onlyNumbers() shouldEqual exp5

        val s6 = "1.1.1"
        val exp6 = ""

        s6.onlyNumbers() shouldEqual exp6
    }

    @Test
    fun `should format a number according to a locale`() {
        val n1 = "1532.0"
        val exp1 = "1.532"

        n1.toLocaleNumber("pt", "BR") shouldEqual exp1

        val n2: String? = null
        val exp2 = ""
        n2.toLocaleNumber("pt", "BR") shouldEqual exp2
    }

    @Test
    fun `should format a currency according to a locale`() {
        val n1 = "U$1532.0"
        val exp1 = "R$ 1.532,00"

        n1.toLocaleCurrency("pt", "BR") shouldEqual exp1

        val n2 = "R$15.32"
        val exp2 = "$15.32"

        n2.toLocaleCurrency("en", "US") shouldEqual exp2

        val n3: String? = null
        val exp3 = ""
        n3.toLocaleCurrency("pt", "BR") shouldEqual exp3
    }

    @Test
    fun `should use a fallback string`() {
        val s1 = " "
        val f1 = "leeeeeroy jeeeeenkins!"

        (s1 ifBlankOrNull f1) shouldEqual f1
        (s1 ifEmptyOrNull f1) shouldEqual s1

        val s2: String? = null
        val f2 = "test"

        (s2 ifBlankOrNull f2) shouldEqual f2
        (s2 ifEmptyOrNull f2) shouldEqual f2

        val s3 = "hi"
        val f3 = "nope"

        (s3 ifBlankOrNull f3) shouldEqual s3
        (s3 ifEmptyOrNull f3) shouldEqual s3
    }
}