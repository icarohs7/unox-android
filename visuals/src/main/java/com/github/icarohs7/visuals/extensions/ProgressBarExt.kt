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

import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.github.icarohs7.core.toplevel.onUi

/**
 * Execute a function, showing the progress bar before starting and hiding
 * it when finished
 */
fun ProgressBar.loadingTransaction(hiddenState: Int = View.GONE, fn: (ProgressBar) -> Unit) {
    try {
        this.isVisible = true
        fn(this)
    } finally {
        this.visibility = hiddenState
    }
}

/**
 * Execute a suspending function, showing the progress bar before starting and
 * hiding it when finished
 */
suspend fun ProgressBar.loadingTransactionAsync(hiddenState: Int = View.GONE, fn: suspend (ProgressBar) -> Unit) {
    try {
        onUi { this.isVisible = true }.join()
        fn(this)
    } finally {
        onUi { this.visibility = hiddenState }.join()
    }
}