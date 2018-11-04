package com.github.icarohs7.visuals.view.activities

import android.view.MenuItem
import com.github.icarohs7.visuals.DummyTestApplication
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import se.lovef.assert.v1.shouldEqual

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = DummyTestApplication::class)
class BaseStandardNxActivityTest {

    @Test
    @Ignore("Robolectric can't found DataBinderMapper class")
    fun `should initialize`() {
        val act = Robolectric.setupActivity(ActivityImpl::class.java)
        act.isFinishing shouldEqual false
    }

    class ActivityImpl : BaseStandardNxActivity() {
        override fun onSelectMenuItem(menuItemId: MenuItem): Boolean {
            return true
        }
    }
}