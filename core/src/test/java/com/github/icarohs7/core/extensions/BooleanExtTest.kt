package com.github.icarohs7.core.extensions

import org.junit.Test
import se.lovef.assert.v1.fail
import se.lovef.assert.v1.shouldEqual

class BooleanExtTest {
    @Test
    fun `should verify the value of a boolean`() {
        true ifFalseInvoke { fail("true should not be false") }
        false ifTrueInvoke { fail("false should no be true") }

        true ifTrueInvoke { true shouldEqual true } ifFalseInvoke { true shouldEqual false }
        false ifTrueInvoke { false shouldEqual true } ifFalseInvoke { false shouldEqual false }
    }
}