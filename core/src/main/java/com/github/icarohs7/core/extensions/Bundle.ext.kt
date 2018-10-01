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

import android.os.Bundle
import android.os.Parcelable

/**
 * Add a pair to a Bundle, with side effects
 */
operator fun <T> Bundle.plusAssign(pair: Pair<String, T>) {
    try {
        val bundle = Bundle() + pair
        this.putAll(bundle)
    } catch (e: IllegalArgumentException) {
    }
}

/**
 * Creates a new bundle with the contents of the first plus
 * the pair added, without modifying the former
 */
operator fun <T> Bundle.plus(pair: Pair<String, T>): Bundle {
    val newBundle = Bundle()
    val (key, valueToAdd) = pair

    when (valueToAdd) {

        is Int -> {
            newBundle.putInt(key, valueToAdd)
        }

        is IntArray -> {
            newBundle.putIntArray(key, valueToAdd)
        }

        is Long -> {
            newBundle.putLong(key, valueToAdd)
        }

        is LongArray -> {
            newBundle.putLongArray(key, valueToAdd)
        }

        is Float -> {
            newBundle.putFloat(key, valueToAdd)
        }

        is FloatArray -> {
            newBundle.putFloatArray(key, valueToAdd)
        }

        is Byte -> {
            newBundle.putByte(key, valueToAdd)
        }

        is ByteArray -> {
            newBundle.putByteArray(key, valueToAdd)
        }

        is Short -> {
            newBundle.putShort(key, valueToAdd)
        }

        is ShortArray -> {
            newBundle.putShortArray(key, valueToAdd)
        }

        is Bundle -> {
            newBundle.putBundle(key, valueToAdd)
        }

        is Char -> {
            newBundle.putChar(key, valueToAdd)
        }

        is CharArray -> {
            newBundle.putCharArray(key, valueToAdd)
        }

        is String -> {
            newBundle.putString(key, valueToAdd)
        }

        is CharSequence -> {
            newBundle.putCharSequence(key, valueToAdd)
        }

        is Parcelable -> {
            newBundle.putParcelable(key, valueToAdd)
        }

        is Boolean -> {
            newBundle.putBoolean(key, valueToAdd)
        }

        is BooleanArray -> {
            newBundle.putBooleanArray(key, valueToAdd)
        }

        else -> {
            throw IllegalArgumentException("Value can't be added to Bundle")
        }
    }

    return newBundle
}