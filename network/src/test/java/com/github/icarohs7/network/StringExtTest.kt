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
import com.github.icarohs7.network.extensions.httpGetObjectAsync
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

class StringExtTest : StringSpec() {
    init {
        val url = "jsonplaceholder.typicode.com/posts/1"
        val expectedObject = Todo(
                1, 1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )

        "should make get requests" {
            launch(CommonPool) { url.httpGetObjectAsync<Todo>().await() shouldBe expectedObject }
        }
    }


    private data class Todo(
            @Json(name = "userId")
            val userId: Int = 0,
            @Json(name = "id")
            val id: Int = 0,
            @Json(name = "title")
            val title: String = "",
            @Json(name = "body")
            val body: String = ""
    )
}