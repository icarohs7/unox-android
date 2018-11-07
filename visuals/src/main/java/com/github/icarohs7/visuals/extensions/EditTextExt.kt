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

package com.github.icarohs7.visuals.extensions

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * Run a function when the enter is pressed on the soft keyboard
 */
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

/**
 * When the ime options of the edit text is set to next,
 * runs this function when the enter is pressed on the
 * soft keyboard
 */
fun EditText.onNextPressedListener(fn: (v: View) -> Unit) {
    this.onImeAction(EditorInfo.IME_ACTION_NEXT, fn)
}

/**
 * When the ime options of the edit text is set to done,
 * runs this function when the enter is pressed on the
 * soft keyboard
 */
fun EditText.onDonePressedListener(fn: (v: View) -> Unit) {
    this.onImeAction(EditorInfo.IME_ACTION_DONE, fn)
}

/**
 * Invoked when a certain ime action defined by the [action] parameter
 * is triggered, invoking the function defined by the [fn] parameter
 */
fun EditText.onImeAction(action: Int, fn: (v: View) -> Unit) {
    this.setOnEditorActionListener { v, actionId, _ ->
        if (actionId == action) {
            fn(v)
            true
        } else {
            false
        }
    }
}