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
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.icarohs7.userinterface.R
import com.github.icarohs7.userinterface.databinding.ActivityBaseBinding
import com.github.icarohs7.userinterface.databinding.FragmentBaseWithheaderBinding
import com.github.icarohs7.userinterface.databinding.FragmentBaseWithoutheaderBinding
import com.github.icarohs7.userinterface.databinding.PartialCaptionImageBinding
import com.github.icarohs7.userinterface.databinding.PartialCenterAndBottomConteinerBinding
import com.github.icarohs7.userinterface.databinding.PartialFullscreenMessageBinding
import com.github.icarohs7.userinterface.databinding.PartialLabelTextBinding
import com.github.icarohs7.userinterface.databinding.PartialLoadingBinding
import com.github.icarohs7.userinterface.databinding.PartialSmallCenterContainerBinding
import com.github.icarohs7.userinterface.databinding.PartialSwipeRecyclerBinding
import com.github.icarohs7.userinterface.toplevel.getBinding
import com.github.icarohs7.userinterface.view.dialogs.NXDialogBuilder
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import org.jetbrains.anko.layoutInflater

interface UIProvider {
    fun nxDialog(context: Context, fn: NXDialogBuilder.() -> Unit)

    /**
     * Bindings for the layout templates
     */
    object Bindings {

        fun textLabel(context: Context, label: String = "", text: String = ""): PartialLabelTextBinding {
            val binding = getBinding<PartialLabelTextBinding>(
                    context.layoutInflater,
                    R.layout.partial_label_text)
            binding.label = label
            binding.text = text
            return binding
        }

        fun swipeRecycler(context: Context): PartialSwipeRecyclerBinding {
            val binding = getBinding<PartialSwipeRecyclerBinding>(
                    context.layoutInflater,
                    R.layout.partial_swipe_recycler)

            binding.recyclerPartial.layoutManager = LinearLayoutManager(context)

            return binding
        }

        fun fullscreenMessage(context: Context, message: String = ""): PartialFullscreenMessageBinding {
            val binding = getBinding<PartialFullscreenMessageBinding>(
                    context.layoutInflater,
                    R.layout.partial_fullscreen_message)
            binding.text = message
            return binding
        }

        fun centerContainer(context: Context): PartialSmallCenterContainerBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.partial_small_center_container)
        }

        fun centerAndBottomContainer(context: Context): PartialCenterAndBottomConteinerBinding {
            return getBinding(
                    context.layoutInflater,
                    R.layout.partial_center_and_bottom_conteiner)
        }

        fun captionImage(context: Context, caption: String = "", drawable: Drawable): PartialCaptionImageBinding {
            val binding = getBinding<PartialCaptionImageBinding>(
                    context.layoutInflater,
                    R.layout.partial_small_center_container)
            binding.caption = caption
            binding.imgImage.setImageDrawable(drawable)
            return binding
        }

        fun captionImage(context: Context, caption: String = "", drawableRes: Int): PartialCaptionImageBinding {
            val binding = getBinding<PartialCaptionImageBinding>(
                    context.layoutInflater,
                    R.layout.partial_small_center_container)
            binding.caption = caption
            binding.imgImage.setImageResource(drawableRes)
            return binding
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

        fun loadingSpinner(
                context: Context,
                message: String = "",
                drawable: Sprite = FadingCircle()
        ): PartialLoadingBinding {

            val binding = getBinding<PartialLoadingBinding>(context.layoutInflater, R.layout.partial_loading)
            binding.txtDescription.text = message
            binding.progressSpinner.setIndeterminateDrawable(drawable)
            return binding
        }

    }
}