package com.github.icarohs7.unoxandroid.extensions

import arrow.core.Try
import org.junit.Test
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.throws

class StringExtensionsKtTest {

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

        val s4 = " "
        val f4 = "leeeeeroy jeeeeenkins!"

        (s4 ifBlankOrNull { f4 }) shouldEqual f4
        (s4 ifEmptyOrNull { f4 }) shouldEqual s4

        val s5: String? = null
        val f5 = "test"

        (s5 ifBlankOrNull { f5 }) shouldEqual f5
        (s5 ifEmptyOrNull { f5 }) shouldEqual f5

        val s6 = "hi"
        val f6 = "nope"

        (s6 ifBlankOrNull { f6 }) shouldEqual s6
        (s6 ifEmptyOrNull { f6 }) shouldEqual s6

        val s7 = " "
        val r7 = Try { s7 ifBlankOrNull { throw IllegalStateException() } }
        ;{ r7.orThrow() } throws IllegalStateException::class

        val s8 = ""
        val r8 = Try { s8 ifEmptyOrNull { throw IllegalArgumentException() } }
        ;{ r8.orThrow() } throws IllegalArgumentException::class
    }

    @Test
    fun find() {
        val s1 = "hello, wor1.2.3ld".find(Regex("\\d.\\d.\\d"))
        val exp1 = "1.2.3"
        s1 shouldEqual exp1

        val s2 = "omai wa mou shindeiru!".find(Regex("o\\w{2}i"))
        val exp2 = "omai"
        s2 shouldEqual exp2

        val s3 = "15lsdkasdklskdlklsadk32osdaklkdlksdlksld99".find(Regex("\\w{2}\\d+"))
        val exp3 = "dk32"
        s3 shouldEqual exp3
    }
}