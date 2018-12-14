package com.github.icarohs7.library

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class TopLevelKtTest {

    @Test
    fun mutableLiveDataOf() {
        val ld1 = mutableLiveDataOf("HI!")
        ld1.value shouldEqual "HI!"

        val ld2 = mutableLiveDataOf(1532)
        ld2.value shouldEqual 1532

        val ld3 = mutableLiveDataOf { s: String -> s.toUpperCase() }
        ld3.value?.invoke("omg") shouldEqual "OMG"
    }
}