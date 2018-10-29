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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * Execute a suspending function, showing the progress bar before starting and
 * hiding it when finished
 */
suspend fun ProgressBar.loadingTransaction(hiddenState: Int = View.GONE, fn: suspend (ProgressBar) -> Unit) {
    coroutineScope {
        try {
            launch(Dispatchers.Main) { startLoading() }.join()
            fn(this@loadingTransaction)
        } finally {
            launch(Dispatchers.Main) { stopLoading(hiddenState) }.join()
        }
    }
}

/**
 * Show the progress bar
 */
fun ProgressBar.startLoading() {
    isVisible = true
}

/**
 * Hide the progress bar, toggling its visibility to the
 * parameterized value or [View.GONE] by default
 */
fun ProgressBar.stopLoading(hiddenState: Int = View.GONE) {
    visibility = hiddenState
}