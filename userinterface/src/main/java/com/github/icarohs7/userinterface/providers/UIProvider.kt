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

package com.github.icarohs7.userinterface.providers

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.icarohs7.userinterface.R
import com.github.icarohs7.userinterface.databinding.ActivityBaseBinding
import com.github.icarohs7.userinterface.databinding.FragmentBaseWithheaderBinding
import com.github.icarohs7.userinterface.databinding.FragmentBaseWithoutheaderBinding
import com.github.icarohs7.userinterface.databinding.PartialFullscreenImageBinding
import com.github.icarohs7.userinterface.databinding.PartialFullscreenMessageBinding
import com.github.icarohs7.userinterface.databinding.PartialLabelTextBinding
import com.github.icarohs7.userinterface.databinding.PartialLoadingBinding
import com.github.icarohs7.userinterface.databinding.PartialSwipeRecyclerBinding
import com.github.icarohs7.userinterface.dialogs.NXDialogBuilder
import com.github.icarohs7.userinterface.toplevel.getBinding
import org.jetbrains.anko.layoutInflater

interface UIProvider {
    fun nxDialog(context: Context, fn: NXDialogBuilder.() -> Unit)

    object Bindings {

        fun textLabel(context: Context): PartialLabelTextBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.partial_label_text)
        }

        fun swipeRecycler(context: Context): PartialSwipeRecyclerBinding {
            val binding = getBinding<PartialSwipeRecyclerBinding>(
                    context.layoutInflater,
                    R.layout.partial_swipe_recycler)

            binding.recyclerPartial.layoutManager = LinearLayoutManager(context)

            return binding
        }

        fun fullscreenMessage(context: Context): PartialFullscreenMessageBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.partial_fullscreen_message)
        }

        fun fullscreenImage(context: Context): PartialFullscreenImageBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.partial_fullscreen_image)
        }

        fun containerWithHeader(context: Context): FragmentBaseWithheaderBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.fragment_base_withheader)
        }

        fun containerWithoutHeader(context: Context): FragmentBaseWithoutheaderBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.fragment_base_withoutheader)
        }

        fun activityBase(context: Context): ActivityBaseBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.activity_base)
        }

        fun loadingSpinner(context: Context): PartialLoadingBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.partial_loading)
        }

    }
}