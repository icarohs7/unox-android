package com.github.icarohs7.unoxcore.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.unoxcore.UnoxCore
import com.github.icarohs7.unoxcore.extensions.coroutines.onForeground
import com.github.icarohs7.unoxcore.testutils.TestApplication
import com.github.icarohs7.unoxcore.testutils.mockActivity
import io.reactivex.subscribers.TestSubscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import se.lovef.assert.v1.shouldEqual


@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class LiveDataExtensionsKtTest {
    @Test
    fun should_return_livedata_value_or_fallback(): Unit = runBlocking<Unit> {
        UnoxCore.foregroundDispatcher = Dispatchers.Main
        val l1 = MutableLiveData<String>()
        l1.value shouldEqual null
        l1.valueOr("Omai wa mou shindeiru!") shouldEqual "Omai wa mou shindeiru!"

        val l2 = MutableLiveData<Int>()
        onForeground { l2.value = 42 }
        l2.valueOr(1532) shouldEqual 42

        val l3 = MutableLiveData<String>()
        onForeground { l3.value = null }
        l3.value shouldEqual null
        l3.valueOr("NANI!?") shouldEqual "NANI!?"
    }

    @Test
    fun should_observe_live_data_with_lambda_and_non_nullable_value() {
        val (controller, activity) = mockActivity<AppCompatActivity>()
        controller.resume()
        var count = 0
        var lastValue = ""
        val liveData = MutableLiveData<String>()
        liveData.observe(activity) {
            count++
            lastValue = it
        }

        count shouldEqual 0
        lastValue shouldEqual ""

        liveData.value = "Omai wa mou shindeiru!"
        count shouldEqual 1
        lastValue shouldEqual "Omai wa mou shindeiru!"

        liveData.value = "NANI!?"
        count shouldEqual 2
        lastValue shouldEqual "NANI!?"

        liveData.value = null
        count shouldEqual 2
        lastValue shouldEqual "NANI!?"
    }

    @Test
    fun should_convert_live_data_to_flowable() {
        val (controller, act) = mockActivity<AppCompatActivity>()
        controller.resume()
        val ld1 = MutableLiveData<String>("NANI!?")
        val f1 = ld1.toFlowable(act)
        val subs1 = TestSubscriber.create<String>()
        f1.subscribe(subs1)
        subs1.assertNotComplete()
        ld1.value = "OMAI WA MOU SHINDEIRU!"
        subs1.assertValues("NANI!?", "OMAI WA MOU SHINDEIRU!")
    }
}