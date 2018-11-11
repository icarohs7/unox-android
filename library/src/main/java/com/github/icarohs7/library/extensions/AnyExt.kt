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
package com.github.icarohs7.library.extensions

import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties


/**
 * Return a map representation with the keys being the name of the
 * properties or the value of the parameterized lambda applied to the property
 */
inline fun <reified T : Any> T.toMapFromProperties(
        propertyNameMapping: (KProperty<*>) -> String = { "" }
): Map<String, String> {
    return T::class.memberProperties.map { prop ->

        val propertyLabel = propertyNameMapping(prop) ifBlankOrNull prop.name
        val propertyValue = prop.get(this).toString()

        propertyLabel to propertyValue

    }.toMap()
}