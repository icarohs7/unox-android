/*
 * MIT License
 *
 * Copyright (c) 2018 Icaro R D Temponi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.icarohs7.network

import com.beust.klaxon.Json
import com.github.icarohs7.core.toplevel.onBgNoReturn
import com.github.icarohs7.network.extensions.httpGetArrayAsync
import com.github.icarohs7.network.extensions.httpGetObjectAsync
import com.github.icarohs7.network.extensions.parseJsonToArray
import com.github.icarohs7.network.extensions.parseJsonToObj
import org.junit.Test
import se.lovef.assert.v1.shouldBeGreaterThan
import se.lovef.assert.v1.shouldBeNull
import se.lovef.assert.v1.shouldEqual
import se.lovef.assert.v1.shouldNotEqual

class StringExtTest {
    @Test
    fun `should do a get request`() {
        val url = "jsonplaceholder.typicode.com/posts/1"
        val expectedObject = Post(
                1, 1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )

        onBgNoReturn { url.httpGetObjectAsync<Post>().await() shouldEqual expectedObject }
    }

    @Test
    fun `should do a get request receiving a list as response`() {
        val url = "jsonplaceholder.typicode.com/posts/"

        val postsReceived = url.httpGetArrayAsync<Post>()

        onBgNoReturn { postsReceived.await().size.shouldBeGreaterThan(0) }
    }

    @Test
    fun `should parse json objects`() {
        val json1 = """{"name":"icaro"}"""
        val obj1 = json1.parseJsonToObj<SimpleObj>()
        val expect1 = SimpleObj("icaro")
        obj1 shouldEqual expect1

        val json2 = """{"invalid":"wont work"}"""
        val obj2 = json2.parseJsonToObj<SimpleObj>()
        val expect2 = SimpleObj("wont work")
        obj2 shouldNotEqual expect2
        obj2.shouldBeNull()
    }

    @Test
    fun `should parse json arrays`() {
        val json1 = """[{"name":"icaro"},{"name":"filipe"}]"""
        val arr1 = json1.parseJsonToArray<SimpleObj>()
        val expect1 = listOf(SimpleObj("icaro"), SimpleObj("filipe"))
        arr1 shouldEqual expect1
        arr1.size shouldEqual 2

        val json2 = """[1,2,3,4,5,6,7,8,9,10]"""
        val arr2 = json2.parseJsonToArray<Int>()
        val expect2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        arr2 shouldEqual expect2
        arr2.size shouldEqual 10
    }

    data class Post(
            @Json(name = "userId")
            val userId: Int = 0,
            @Json(name = "id")
            val id: Int = 0,
            @Json(name = "title")
            val title: String = "",
            @Json(name = "body")
            val body: String = ""
    )

    data class SimpleObj(val name: String)
}