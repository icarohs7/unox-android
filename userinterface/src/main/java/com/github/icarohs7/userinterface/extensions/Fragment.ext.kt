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

package com.github.icarohs7.userinterface.extensions

import android.view.View
import androidx.fragment.app.Fragment
import org.jetbrains.anko.inputMethodManager

/**
 * Dismisses the soft keyboard
 */
fun Fragment.hideKeyboard(containerId: Int) {
    requireActivity()
            .inputMethodManager
            .hideSoftInputFromWindow(requireActivity().findViewById<View>(containerId)?.windowToken, 0)
}

/**
 * Dismisses the soft keyboard
 */
fun Fragment.hideKeyboard(container: View) {
    requireActivity()
            .inputMethodManager
            .hideSoftInputFromWindow(container.windowToken, 0)
}

/**
 * Get the list of arguments of the fragment and return it as
 * a list of pairs
 */
val Fragment.argumentList: List<Pair<String, Any>>
    get() {
        return arguments
                ?.keySet()
                ?.mapNotNull { key ->
                    val value = arguments?.get(key)
                    if (value != null) {
                        key to value
                    } else {
                        null
                    }
                }
                ?: emptyList()
    }
