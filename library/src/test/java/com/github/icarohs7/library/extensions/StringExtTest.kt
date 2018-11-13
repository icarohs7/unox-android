package com.github.icarohs7.library.extensions

import arrow.core.orNull
import org.junit.Test
import se.lovef.assert.v1.shouldBeCloseTo
import se.lovef.assert.v1.shouldEqual
import java.util.TimeZone

class StringExtTest {

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

    @Test
    fun parseDate() {
        val s1 = "2018-10-30 11:00:00".parseDate().orNull()!!.time
        val s2 = "2018-10-30 11:01:00".parseDate().orNull()!!.time
        (s1 + 60000) shouldBeCloseTo s2 tolerance 1000

        val s3 = "1997-01-01 00:00:00".parseDate().orNull()!!.time
        val s4 = "1997-01-01 00:10:00".parseDate().orNull()!!.time
        (s3 + 600000) shouldEqual s4
    }

    @Test
    fun parseDateMilis() {
        //Given
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"))
        val d1 = "1970-01-01 00:00:00"
        val d2 = "1997-05-09 00:00:00"

        //When
        val milis1 = d1.parseDateMilis()
        val milisTry1 = d1.parseDateMilisTry()
        val milis2 = d2.parseDateMilis()
        val milisTry2 = d2.parseDateMilisTry()

        //Then
        milis1 shouldEqual milisTry1.orNull()!!
        milis1 shouldEqual 0
        milis2 shouldEqual milisTry2.orNull()!!
        milis2 shouldEqual 863_136_000_000
    }

    @Test
    fun toStringDateWithFormat() {
        val d1 = "2018-05-09 12:00:00"
        val ed1 = "09/05/2018 12:00"
        d1.toStringDateWithFormat("dd/MM/yyyy HH:mm").orNull() shouldEqual ed1

        val d2 = "09/05/18 12:00"
        val ed2 = "2018-05-09 12:00:00"
        d2.toStringDateWithFormat(oldFormat = "dd/MM/yy HH:mm").orNull() shouldEqual ed2
    }
}