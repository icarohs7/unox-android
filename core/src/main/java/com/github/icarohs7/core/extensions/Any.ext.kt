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
package com.github.icarohs7.core.extensions

import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.core.annotations.Label
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * Wraps an object in a LiveData
 */
fun <T> T?.asLiveData() =
        MutableLiveData<T>().also { it.value = this }

/**
 * Return a map representation with the keys being the name of the
 * properties or the value of the annotation [Label] and the values
 * being the values of the properties
 */
inline fun <reified T : Any> T.mapOfProperties(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    val clazz = T::class
    clazz.memberProperties.forEach { prop ->
        val label = prop.findAnnotation<Label>()?.value ?: prop.name
        map += label to prop.get(this).toString()
    }
    return map
}

/**
 * Extension property returning the simple name of the class
 */
val Any?.TAG: String
    get() = this?.let { obj -> obj::class.simpleName } ?: "null"