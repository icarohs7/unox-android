package com.github.icarohs7.app.ui.view

import com.github.icarohs7.app.testutils.BaseActivityTestClass
import com.github.icarohs7.app.testutils.blockingDelay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test
import se.lovef.assert.v1.shouldBeTrue

class TestActivityTest : BaseActivityTestClass<TestActivity>(TestActivity::class) {
    @Test
    fun coroutine_scope_should_restrain_coroutines_lifetime() {
        //Given
        launchAct()
        //When
        blockingDelay(200)
        val coroutine = activity.launch { delay(5000) }
        rule.finishActivity()
        blockingDelay(800)
        //Then
        coroutine.isCancelled.shouldBeTrue()
    }
}