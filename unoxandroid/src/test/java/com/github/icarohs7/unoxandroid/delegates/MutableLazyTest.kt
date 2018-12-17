package com.github.icarohs7.unoxandroid.delegates

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class MutableLazyTest {
    private var v: Int by mutableLazy { 10 }

    @Test
    fun `should initialize variable lazily`() {
        //Given v
        //Then
        v shouldEqual 10
    }

    @Test
    fun `should reassign lazy variable`() {
        //Given v
        v shouldEqual 10
        //When
        v = 1532
        //Then
        v shouldEqual 1532
    }
}