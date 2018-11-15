package com.github.icarohs7.library.delegates

import org.junit.Test
import se.lovef.assert.v1.shouldBeFalse
import se.lovef.assert.v1.shouldBeTrue
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.jvm.isAccessible

class HelpersTest {
    object TestObj {
        private var testProp = "hi"
        val propRef: KMutableProperty0<String> = this::testProp
    }

    @Test
    fun `make a property visible for usage and then invisible again`() {
        //Given
        val propRef = TestObj.propRef

        //Then
        propRef.isAccessible.shouldBeFalse()
        propRef.accessibleTransaction { propRef.isAccessible.shouldBeTrue() }
        propRef.isAccessible.shouldBeFalse()
    }
}