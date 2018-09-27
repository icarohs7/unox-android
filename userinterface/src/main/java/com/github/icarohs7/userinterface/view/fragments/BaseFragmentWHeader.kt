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

package com.github.icarohs7.userinterface.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.github.icarohs7.userinterface.providers.UIProvider
import com.google.android.material.card.MaterialCardView

/**
 * Base fragment with a card header and a body
 */
abstract class BaseFragmentWHeader : Fragment() {
    protected lateinit var headerView: MaterialCardView
    protected lateinit var contentView: FrameLayout

    abstract fun onBindingCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootBinding = UIProvider.Bindings.containerWithHeader(requireContext())

        headerView = rootBinding.baseHeaderHeader
        contentView = rootBinding.baseHeaderContainer

        onBindingCreated(inflater, container, savedInstanceState)
        return rootBinding.root
    }
}