package com.github.icarohs7.core.delegates

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class PropertyRedirectionDelegateTest {
    private var target1 = "hello"
    private var mirror1 by redirectToProperty(::target1)

    private var target2 = "omai"
    private var target3 = "wa"
    private var target4 = "mou"
    private var target5 = "shindeiru"
    private var mirror2 by redirectToProperty { arrayOf(::target2, ::target3, ::target4, ::target5) }

    @Test
    fun getValue() {
        mirror1 shouldEqual "hello"
        mirror2 shouldEqual "omai"
    }

    @Test
    fun setValue() {
        mirror1 = "WTF!"
        mirror1 shouldEqual "WTF!"
        target1 shouldEqual "WTF!"

        mirror2 = "omai wa mou shindeiru"
        target2 shouldEqual "omai wa mou shindeiru"
        target3 shouldEqual "omai wa mou shindeiru"
        target4 shouldEqual "omai wa mou shindeiru"
        target5 shouldEqual "omai wa mou shindeiru"
    }
}