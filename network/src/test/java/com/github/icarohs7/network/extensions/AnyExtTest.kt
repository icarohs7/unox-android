package com.github.icarohs7.network.extensions

import com.beust.klaxon.Json
import com.github.icarohs7.network.annotations.Label
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class AnyExtTest {

    @Test
    fun `should convert a class to a map`() {
        val firstObj = TestClass3("a", 1, "b")
        val firstMap = firstObj.mapOfProperties()
        val firstMapExpected = mapOf(
                "foo" to "a",
                "bar" to "1",
                "hi" to "b"
        )
        firstMap shouldEqual firstMapExpected

        val secondObj = TestClass3("x", 0, "p")
        val secondMap = secondObj.mapOfProperties()
        val secondMapExpected = mapOf(
                "foo" to "x",
                "bar" to "0",
                "hi" to "p"
        )
        secondMap shouldEqual secondMapExpected

        val thirdObj = TestClass4(5, "hi")
        val thirdMap = thirdObj.mapOfProperties()
        val thirdMapExpected = mapOf(
                "bar" to "5",
                "someth" to "hi"
        )
        thirdMap shouldEqual thirdMapExpected
    }

    @Test
    fun `should convert an object to its json representation`() {
        val obj1 = TestClass("hi", 10)
        val exp1 = """{"age" : 10, "name" : "hi"}"""

        obj1.toJson() shouldEqual exp1

        val obj2 = listOf("hi", "there", "let's", "do", "TDD")
        val exp2 = """["hi", "there", "let's", "do", "TDD"]"""

        obj2.toJson() shouldEqual exp2

        val obj3 = TestClass("omai wa mou shindeiru", 1532)
        val exp3 = """{"age" : 1532, "name" : "omai wa mou shindeiru"}"""

        obj3.toJson() shouldEqual exp3

        val obj4 = TestClass2(3.1415, listOf(5, 4, 3, 2, 1))
        val exp4 = """{"attr1" : 3.1415, "attr2" : [5, 4, 3, 2, 1]}"""

        obj4.toJson() shouldEqual exp4
    }

    data class TestClass(val name: String, val age: Int)
    data class TestClass2(val attr1: Double, val attr2: List<Int>)
    data class TestClass3(val foo: String, val bar: Int, @Label("hi") val baz: String)
    data class TestClass4(@Json(name = "bar") val foo: Int, val someth: String)
}