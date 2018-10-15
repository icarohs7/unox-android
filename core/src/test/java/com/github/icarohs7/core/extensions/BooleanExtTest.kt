package com.github.icarohs7.core.extensions

import org.junit.Test
import se.lovef.assert.v1.fail
import se.lovef.assert.v1.shouldEqual

class BooleanExtTest {
    @Test
    fun `should verify the value of a boolean`() {
        true ifFalse { fail("true should not be false") }
        false ifTrue { fail("false should no be true") }

        true ifTrue { true shouldEqual true } ifFalse { true shouldEqual false }
        false ifTrue { false shouldEqual true } ifFalse { false shouldEqual false }
    }
}