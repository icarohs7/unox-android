package com.github.icarohs7.app.ui.view

import com.github.icarohs7.app.testutils.BaseActivityTestClass
import com.github.icarohs7.app.testutils.blockingDelay
import com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsClosed
import com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsOpen
import com.schibsted.spain.barista.interaction.BaristaDrawerInteractions.closeDrawer
import com.schibsted.spain.barista.interaction.BaristaDrawerInteractions.openDrawer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test
import se.lovef.assert.v1.shouldBeTrue

class BaseStandardNxActivityImplTest : BaseActivityTestClass<BaseStandardNxActivityImpl>(
        BaseStandardNxActivityImpl::class
) {
    @Test
    fun should_open_close_drawer() {
        //Given
        launchAct()
        blockingDelay(200)
        //When
        assertDrawerIsClosed()
        openDrawer()
        //Then
        assertDrawerIsOpen()

        //When
        closeDrawer()
        assertDrawerIsClosed()
    }

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