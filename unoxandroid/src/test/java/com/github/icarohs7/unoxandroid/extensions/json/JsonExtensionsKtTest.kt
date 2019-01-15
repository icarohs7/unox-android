package com.github.icarohs7.unoxandroid.extensions.json

import com.github.icarohs7.unoxandroid.extensions.orThrow
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class JsonExtensionsKtTest {
    @Test
    fun `parse json string`() {
        //Given
        val j1 = """[{"name": "Icaro"}, {"name": "Filipe"}]"""
        val j2 = """{"name": "Icaro", "age": 21, "class":"Mage"}"""
        //When
        val r1 = j1.parseJson().orThrow()
        val r2 = j2.parseJson().orThrow()
        //Then
        r1[0]["name"].str() shouldEqual "Icaro"
        r1[1, "name"].str() shouldEqual "Filipe"
        r2["name"].str() shouldEqual "Icaro"
        r2["age"].int() shouldEqual 21
        r2["class"].str() shouldEqual "Mage"
    }

    @Test
    fun `access string and int properties`() {
        //Given
        val j1 = """{"name":"Icaro", "age":21}"""
        val j2 = """[{"message":"Omai wa mou shindeiru"},{"answer":42}]"""
        //When
        val r1 = j1.parseJson().orThrow()
        val r2 = j2.parseJson().orThrow()
        //Then
        r1["name"].str() shouldEqual "Icaro"
        r1["age"].int() shouldEqual 21
        r2[0, "message"].str() shouldEqual "Omai wa mou shindeiru"
        r2[1, "answer"].int() shouldEqual 42
    }
}