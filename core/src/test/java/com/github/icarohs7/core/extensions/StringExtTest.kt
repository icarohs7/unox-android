package com.github.icarohs7.core.extensions

import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class StringExtTest {
    @Test
    fun `should use a fallback string`() {
        val s1 = " "
        val f1 = "leeeeeroy jeeeeenkins!"

        (s1 ifBlankOrNull f1) shouldEqual f1
        (s1 ifEmptyOrNull f1) shouldEqual s1

        val s2: String? = null
        val f2 = "test"

        (s2 ifBlankOrNull f2) shouldEqual f2
        (s2 ifEmptyOrNull f2) shouldEqual f2

        val s3 = "hi"
        val f3 = "nope"

        (s3 ifBlankOrNull f3) shouldEqual s3
        (s3 ifEmptyOrNull f3) shouldEqual s3
    }
}