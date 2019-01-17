package com.github.icarohs7.unoxandroid.extensions.json

import arrow.core.Try
import com.github.icarohs7.unoxandroid.extensions.successValues
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class JsonIntegrationsKtTest {
    @Test
    fun `access json keys inside try`() {
        //Given
        val j1 = """[{"name": "Icaro"}, {"name": "Filipe"}]"""
        val j2 = """{"name": "Icaro", "age": 21, "class":"Mage"}"""
        val j3 = """[1,2,3,4,5]"""
        //When
        val r1: Try<JsonInstance> = j1.parseJson()
        val r2: Try<JsonInstance> = j2.parseJson()
        val r3: Try<JsonInstance> = j3.parseJson()
        //Then
        r1[0]["name"].str() shouldEqual "Icaro"
        r1[1, "name"].str() shouldEqual "Filipe"
        r2["name"].str() shouldEqual "Icaro"
        r2["age"].int() shouldEqual 21
        r2["class"].str() shouldEqual "Mage"
        r3[0].int() shouldEqual 1
        r3[1].int() shouldEqual 2
        r3[2].int() shouldEqual 3
        r3[3].int() shouldEqual 4
        r3[4].int() shouldEqual 5
    }

    @Test
    fun `access string and int properties`() {
        //Given
        val j1 = """{"name":"Icaro", "age":21}"""
        val j2 = """[{"message":"Omai wa mou shindeiru"},{"answer":42}]"""
        //When
        val r1: Try<JsonInstance> = j1.parseJson()
        val r2: Try<JsonInstance> = j2.parseJson()
        //Then
        r1["name"].str() shouldEqual "Icaro"
        r1["age"].int() shouldEqual 21
        r2[0, "message"].str() shouldEqual "Omai wa mou shindeiru"
        r2[1, "answer"].int() shouldEqual 42
    }

    @Test
    fun `wrapped value to list`() {
        //Given
        val j1 = "[1,2,3,4]"
        val j2 = """["Icaro", "Geandro", "Filipe", "Isaque", "Hugo"]"""
        //When
        val r1 = j1.parseJson()
        val r2 = j2.parseJson()
        //Then
        var c1 = 1
        r1.asList().successValues().forEach { it.int() shouldEqual c1++ }
        var c2 = 0
        r2.asList().successValues().forEach { it.str() shouldEqual r2[c2++].str() }
    }
}