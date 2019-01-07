package com.github.icarohs7.app.ui.view

import com.github.icarohs7.app.testutils.BaseFragmentTestClass
import com.github.icarohs7.unoxandroid.extensions.views.loadFragmentWithoutBack
import org.junit.Test
import se.lovef.assert.v1.shouldBe
import se.lovef.assert.v1.shouldNotBe

class BaseBindingFragmentImplTest
    : BaseFragmentTestClass<BaseStandardNxActivityImpl, BaseBindingFragmentImpl>(BaseStandardNxActivityImpl::class) {

    @Test
    fun should_not_allow_reloading_fragment() {
        //Given
        val frag = fragment
        //When
        activity.loadFragmentWithoutBack(BaseBindingFragmentImpl(), allowLoadingFragmentTwiceInARow = false)
        //Then
        fragment shouldNotBe frag
    }

    @Test
    fun should_allow_reloading_fragment() {
        //Given
        val frag = fragment
        //When
        activity.loadFragmentWithoutBack(BaseBindingFragmentImpl(), allowLoadingFragmentTwiceInARow = true)
        //Then
        fragment shouldBe frag
    }
}