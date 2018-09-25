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

package com.github.icarohs7.contractwatcher.view.activities

import androidx.databinding.DataBindingUtil
import com.github.icarohs7.contractwatcher.R
import com.github.icarohs7.userinterface.databinding.ActivityBaseBinding

open class BaseContractWatcherActivity : ContractWatcherActivity() {
    lateinit var binding: ActivityBaseBinding

    override fun onSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        setupViews()
    }

    private fun setupViews() {
        bottomNavigationView = binding.baseactBottomnav
        drawerLayout = binding.baseactDrawerLayout
        sideNavigationView = binding.baseactSidenav
        toolbar = binding.baseactToolbar
        toolbarOpenDrawerMenuItemDrawableId = R.drawable.ic_menu
    }
}