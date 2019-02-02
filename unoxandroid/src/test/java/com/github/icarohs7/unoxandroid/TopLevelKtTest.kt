package com.github.icarohs7.unoxandroid

import android.os.Looper
import com.github.icarohs7.unoxandroid.extensions.coroutines.onForeground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.shouldNotEqual

@RunWith(RobolectricTestRunner::class)
class TopLevelKtTest {
    @Test
    fun `should switch to main thread if not already on it and run operation`(): Unit = runBlocking<Unit> {
        var check1Done = false
        var check2Done = false

        //Should switch threads when not on main already
        withContext(Dispatchers.Default) {
            val outer = Looper.myLooper()
            mustRunOnMainThread {
                val inner = Looper.myLooper()
                inner shouldNotEqual outer
                inner shouldEqual Looper.getMainLooper()
                check1Done = true
            }
        }

        //Should just execute the block when already on main
        onForeground {
            val outer = Looper.myLooper()
            mustRunOnMainThread {
                val inner = Looper.myLooper()
                inner shouldEqual outer
                inner shouldEqual Looper.getMainLooper()
                check2Done = true
            }
        }

        delay(1000)
        check1Done.shouldBeTrue()
        check2Done.shouldBeTrue()
    }
}