package com.github.icarohs7.unoxandroid.extensions

import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.unoxandroid.extensions.coroutines.onForeground
import com.snakydesign.livedataextensions.emptyLiveData
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
class LiveDataExtensionsKtTest {
    @Test
    fun `should return livedata value or fallback`(): Unit = runBlocking<Unit> {
        val l1 = MutableLiveData<String>()
        l1.value shouldEqual null
        l1.valueOr("Omai wa mou shindeiru!") shouldEqual "Omai wa mou shindeiru!"

        val l2 = emptyLiveData<Int>()
        onForeground { l2.value = 42 }
        l2.valueOr(1532) shouldEqual 42

        val l3 = emptyLiveData<String>()
        onForeground { l3.value = null }
        l3.value shouldEqual null
        l3.valueOr("NANI!?") shouldEqual "NANI!?"
    }
}