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

package com.github.icarohs7.templates.extensions

import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.github.icarohs7.core.toplevel.onUi
import com.github.icarohs7.templates.databinding.FragmentBaseWithheaderBinding

/**
 * Execute a function, showing the progress bar before starting and hiding
 * it when finished
 */
fun FragmentBaseWithheaderBinding.loadingTransaction(fn: (ProgressBar) -> Unit) {
    try {
        this.progressBar.isVisible = true
        fn(this.progressBar)
    } finally {
        this.progressBar.isGone = true
    }
}

/**
 * Execute a suspending function, showing the progress bar before starting and
 * hiding it when finished
 */
suspend fun FragmentBaseWithheaderBinding.loadingTransactionAsync(fn: suspend (ProgressBar) -> Unit) {
    try {
        onUi { this.progressBar.isVisible = true }
        fn(this.progressBar)
    } finally {
        onUi { this.progressBar.isGone = true }
    }
}