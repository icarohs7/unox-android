package com.github.icarohs7.library.extensions

import arrow.core.orNull
import arrow.core.success
import org.junit.Test
import se.lovef.assert.v1.shouldBeCloseTo
import se.lovef.assert.v1.shouldEqual
import java.util.TimeZone

class TryExtTest {
    @Test
    fun parseDate() {
        val s1 = "2018-10-30 11:00:00".success().parseDate().orNull()!!.time
        val s2 = "2018-10-30 11:01:00".success().parseDate().orNull()!!.time
        (s1 + 60000) shouldBeCloseTo s2 tolerance 1000

        val s3 = "1997-01-01 00:00:00".success().parseDate().orNull()!!.time
        val s4 = "1997-01-01 00:10:00".success().parseDate().orNull()!!.time
        (s3 + 600000) shouldEqual s4
    }

    @Test
    fun parseDateMilis() {
        //Given
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"))
        val d1 = "1970-01-01 00:00:00".success()
        val d2 = "1997-05-09 00:00:00".success()

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
}