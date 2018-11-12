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

package com.github.icarohs7.library.view.activities

import android.widget.ProgressBar
import com.github.icarohs7.library.R
import com.github.icarohs7.library.databinding.ActivityBaseStandardNxBinding
import com.github.icarohs7.library.entities.ActivityResources
import com.github.icarohs7.library.extensions.hide
import com.github.icarohs7.library.extensions.show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class BaseStandardNxActivity : BaseBindingAndResourceNxActivity<ActivityBaseStandardNxBinding>() {
    /**
     * Execute an operation, showing the progress bar when it's running
     * and hiding it when done
     */
    open suspend fun runWithProgressFeedback(fn: suspend (ProgressBar) -> Unit) {
        try {
            withContext(Dispatchers.Main) { startLoading() }
            fn(binding.progressBar)
        } finally {
            withContext(Dispatchers.Main) { stopLoading() }
        }
    }

    /** Show the progress bar */
    fun startLoading(): Unit = binding.progressBar.show()

    /** Hide the progress bar */
    fun stopLoading(): Unit = binding.progressBar.hide()

    override fun onDefineActivityResources(activityResources: ActivityResources) {
        activityResources.apply {
            bottomNavigationView = binding.bottomNavigation
            drawerLayout = binding.drawerLayout
            navDrawerView = binding.navView
            toolbar = binding.toolbar
            toolbarOpenDrawerMenuItemDrawableId = R.drawable.ic_menu
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_standard_nx
    }
}