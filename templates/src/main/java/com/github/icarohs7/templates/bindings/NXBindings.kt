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

package com.github.icarohs7.templates.bindings

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.icarohs7.templates.R
import com.github.icarohs7.templates.databinding.ActivityBaseBinding
import com.github.icarohs7.templates.databinding.FragmentBaseWithheaderBinding
import com.github.icarohs7.templates.databinding.FragmentBaseWithoutheaderBinding
import com.github.icarohs7.templates.databinding.PartialButtonBinding
import com.github.icarohs7.templates.databinding.PartialCaptionImageBinding
import com.github.icarohs7.templates.databinding.PartialCenterAndBottomConteinerBinding
import com.github.icarohs7.templates.databinding.PartialFullscreenMessageBinding
import com.github.icarohs7.templates.databinding.PartialLabelTextBinding
import com.github.icarohs7.templates.databinding.PartialLoadingBinding
import com.github.icarohs7.templates.databinding.PartialSmallCenterContainerBinding
import com.github.icarohs7.templates.databinding.PartialSwipeRecyclerBinding
import com.github.icarohs7.templates.extensions.inflateBinding
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle

/**
 * Bindings for the layout templates
 */
object NXBindings {

    /**
     * Vertical linear layout with 2 text views
     */
    fun textLabel(context: Context, label: String = "", text: String = ""): PartialLabelTextBinding {
        val binding = context.inflateBinding<PartialLabelTextBinding>(R.layout.partial_label_text)

        binding.label = label
        binding.text = text

        return binding
    }

    /**
     * Swipe refresh layout with a recycler
     */
    fun swipeRecycler(context: Context): PartialSwipeRecyclerBinding {
        val binding = context.inflateBinding<PartialSwipeRecyclerBinding>(R.layout.partial_swipe_recycler)

        binding.recycler.layoutManager = LinearLayoutManager(context)

        return binding
    }

    /**
     * Frame layout with a text view
     */
    fun fullscreenMessage(context: Context, message: String = ""): PartialFullscreenMessageBinding {
        val binding = context.inflateBinding<PartialFullscreenMessageBinding>(R.layout.partial_fullscreen_message)
        binding.text = message
        return binding
    }

    /**
     * Linear layout with a inner frame layout as container
     */
    fun centerContainer(context: Context): PartialSmallCenterContainerBinding {
        return context.inflateBinding(R.layout.partial_small_center_container)
    }

    /**
     * Constraint layout with 2 frame layouts, 1 at the middle and 1 at the bottom
     */
    fun centerAndBottomContainer(context: Context): PartialCenterAndBottomConteinerBinding {
        return context.inflateBinding(R.layout.partial_center_and_bottom_conteiner)
    }

    /**
     * Vertical linear layout with a image view and a text view
     */
    fun captionImage(context: Context, caption: String = "", drawable: Drawable): PartialCaptionImageBinding {
        val binding = context.inflateBinding<PartialCaptionImageBinding>(R.layout.partial_caption_image)

        binding.caption = caption
        binding.imgImage.setImageDrawable(drawable)

        return binding
    }

    /**
     * Vertical linear layout with a image view and a text view
     */
    fun captionImage(context: Context, caption: String = "", drawableRes: Int): PartialCaptionImageBinding {
        val binding = context.inflateBinding<PartialCaptionImageBinding>(R.layout.partial_caption_image)

        binding.caption = caption
        binding.imgImage.setImageResource(drawableRes)

        return binding
    }

    /**
     * Constraint layout with a top hidden progress bar, a header
     * material card view and a frame layout used as main container
     */
    fun containerWithHeader(context: Context): FragmentBaseWithheaderBinding {
        return context.inflateBinding(R.layout.fragment_base_withheader)
    }

    /**
     * Constraint layout with a top hidden progress bar and
     * a frame layout used as main container
     */
    fun containerWithoutHeader(context: Context): FragmentBaseWithoutheaderBinding {
        return context.inflateBinding(R.layout.fragment_base_withoutheader)
    }

    /**
     * Base activity layout with wrapper drawer layout with a
     * navigation view, and a constraint layout containing
     * a toolbar, a frame layout used as main container and
     * a bottom navigation view
     */
    fun activityBase(context: Context): ActivityBaseBinding {
        return context.inflateBinding(R.layout.activity_base)
    }

    /**
     * Vertical linear layout with a spin kit view (loading spinner)
     * and a text view
     */
    fun loadingSpinner(
            context: Context,
            message: String = "",
            drawable: Sprite = FadingCircle()
    ): PartialLoadingBinding {

        val binding = context.inflateBinding<PartialLoadingBinding>(R.layout.partial_loading)

        binding.txtDescription.text = message
        binding.progressSpinner.setIndeterminateDrawable(drawable)

        return binding
    }

    /**
     * Binding with a linear layout wrapping a single button
     */
    fun button(context: Context): PartialButtonBinding {
        return context.inflateBinding(R.layout.partial_button)
    }

}