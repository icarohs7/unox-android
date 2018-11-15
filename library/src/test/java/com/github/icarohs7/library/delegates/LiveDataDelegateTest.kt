package com.github.icarohs7.library.delegates

import com.github.icarohs7.library.mutableLiveDataOf
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
class LiveDataDelegateTest {
    @Test
    fun `should delegate get operations on livedata`() {
        //Given
        val d1 by Eg.ld1
        val d2 by Eg.ld2
        val d3 by Eg.ld3

        //Then
        d1 shouldEqual 5
        d2 shouldEqual "hi"
        d3 shouldEqual mapOf("name" to "Icaro", "country" to "Brazil")
    }

    @Test
    fun `should delegate set operations on livedata`() {
        //Given
        var d1: Int? by Eg.ld1
        var d2: String? by Eg.ld2
        var d3: Map<String, String>? by Eg.ld3

        //When
        d1 = 1532
        d2 = "omai wa mou shindeiru"
        d3 = mapOf("name" to "Bolsonaro")

        //Then
        Eg.ld1.value shouldEqual d1
        Eg.ld1.value shouldEqual 1532
        Eg.ld2.value shouldEqual d2
        Eg.ld2.value shouldEqual "omai wa mou shindeiru"
        Eg.ld3.value shouldEqual d3
        Eg.ld3.value shouldEqual mapOf("name" to "Bolsonaro")
    }

    private object Eg {
        val ld1 = mutableLiveDataOf(5)
        val ld2 = mutableLiveDataOf("hi")
        val ld3 = mutableLiveDataOf(mapOf("name" to "Icaro", "country" to "Brazil"))
    }
}