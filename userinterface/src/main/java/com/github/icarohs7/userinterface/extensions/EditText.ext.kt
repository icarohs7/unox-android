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

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.github.icarohs7.userinterface.callbacks.ViewConsumer

fun EditText.onEnterPressedListener(fn: (v: View) -> Unit) {
    this.setOnKeyListener { v, keyCode, event ->
        if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            fn(v)
            true
        } else {
            false
        }
    }
}

fun EditText.onNextPressedListener(fn: (v: View) -> Unit) {
    this.onImeAction(EditorInfo.IME_ACTION_NEXT, fn)
}

fun EditText.onDonePressedListener(fn: (v: View) -> Unit) {
    this.onImeAction(EditorInfo.IME_ACTION_DONE, fn)
}

private fun EditText.onImeAction(action: Int, fn: (v: View) -> Unit) {
    this.setOnEditorActionListener { v, actionId, _ ->
        if (actionId == action) {
            fn(v)
            true
        } else {
            false
        }
    }
}

//For Javalanders
object EditTextUtils {
    @JvmStatic
    fun setEditTextOnEnterPressedListener(editText: EditText, listener: ViewConsumer) {
        editText.onEnterPressedListener { listener.accept(it) }
    }

    @JvmStatic
    fun setEditTextOnNextPressedListener(editText: EditText, listener: ViewConsumer) {
        editText.onNextPressedListener { listener.accept(it) }
    }

    @JvmStatic
    fun setEditTextOnDonePressedListener(editText: EditText, listener: ViewConsumer) {
        editText.onDonePressedListener { listener.accept(it) }
    }
}