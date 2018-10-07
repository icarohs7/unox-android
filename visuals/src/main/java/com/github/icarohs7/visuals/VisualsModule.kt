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
package com.github.icarohs7.visuals

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.icarohs7.core.typealiases.RConsumer
import com.github.icarohs7.visuals.databinding.ActivityBaseBinding
import com.github.icarohs7.visuals.databinding.DialogYesNoBinding
import com.github.icarohs7.visuals.databinding.FragmentBaseWithheaderBinding
import com.github.icarohs7.visuals.databinding.FragmentBaseWithoutheaderBinding
import com.github.icarohs7.visuals.databinding.PartialCaptionImageBinding
import com.github.icarohs7.visuals.databinding.PartialCenterAndBottomConteinerBinding
import com.github.icarohs7.visuals.databinding.PartialFormFieldBinding
import com.github.icarohs7.visuals.databinding.PartialFormMaskedFieldBinding
import com.github.icarohs7.visuals.databinding.PartialFormPasswordFieldBinding
import com.github.icarohs7.visuals.databinding.PartialFullscreenMessageBinding
import com.github.icarohs7.visuals.databinding.PartialLabelTextBinding
import com.github.icarohs7.visuals.databinding.PartialLoadingBinding
import com.github.icarohs7.visuals.databinding.PartialSmallCenterContainerBinding
import com.github.icarohs7.visuals.databinding.PartialSwipeRecyclerBinding
import com.github.icarohs7.visuals.providers.abstractions.VisualsProvider
import com.github.icarohs7.visuals.providers.implementations.VisualsProviderImpl
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import org.jetbrains.anko.layoutInflater

interface VisualsModule {
    companion object {
        val provider: VisualsProvider get() = VisualsProviderImpl()
    }

    /**
     * Bindings for the layout templates
     */
    object NXBindings {

        /**
         * Vertical linear layout with 2 text views
         */
        fun textLabel(context: Context, label: String = "", text: String = ""): PartialLabelTextBinding {
            val binding = PartialLabelTextBinding.inflate(context.layoutInflater)

            binding.label = label
            binding.text = text

            return binding
        }

        /**
         * Swipe refresh layout with a recycler
         */
        fun swipeRecycler(context: Context): PartialSwipeRecyclerBinding {
            val binding = PartialSwipeRecyclerBinding.inflate(context.layoutInflater)

            binding.recycler.layoutManager = LinearLayoutManager(context)

            return binding
        }

        /**
         * Frame layout with a text view
         */
        fun fullscreenMessage(context: Context, message: String = ""): PartialFullscreenMessageBinding {
            val binding = PartialFullscreenMessageBinding.inflate(context.layoutInflater)
            binding.text = message
            return binding
        }

        /**
         * Linear layout with a inner frame layout as container
         */
        fun centerContainer(context: Context): PartialSmallCenterContainerBinding {
            return PartialSmallCenterContainerBinding.inflate(context.layoutInflater)
        }

        /**
         * Constraint layout with 2 frame layouts, 1 at the middle and 1 at the bottom
         */
        fun centerAndBottomContainer(context: Context): PartialCenterAndBottomConteinerBinding {
            return PartialCenterAndBottomConteinerBinding.inflate(context.layoutInflater)
        }

        /**
         * Vertical linear layout with a image view and a text view
         */
        fun captionImage(context: Context, caption: String = "", drawable: Drawable): PartialCaptionImageBinding {
            val binding = PartialCaptionImageBinding.inflate(context.layoutInflater)

            binding.caption = caption
            binding.imgImage.setImageDrawable(drawable)

            return binding
        }

        /**
         * Vertical linear layout with a image view and a text view
         */
        fun captionImage(context: Context, caption: String = "", drawableRes: Int): PartialCaptionImageBinding {
            val binding = PartialCaptionImageBinding.inflate(context.layoutInflater)

            binding.caption = caption
            binding.imgImage.setImageResource(drawableRes)

            return binding
        }

        /**
         * Constraint layout with a top hidden progress bar, a header
         * material card view and a frame layout used as main container
         */
        fun containerWithHeader(context: Context): FragmentBaseWithheaderBinding {
            return FragmentBaseWithheaderBinding.inflate(context.layoutInflater)
        }

        /**
         * Constraint layout with a top hidden progress bar and
         * a frame layout used as main container
         */
        fun containerWithoutHeader(context: Context): FragmentBaseWithoutheaderBinding {
            return FragmentBaseWithoutheaderBinding.inflate(context.layoutInflater)
        }

        /**
         * Base activity layout with wrapper drawer layout with a
         * navigation view, and a constraint layout containing
         * a toolbar, a frame layout used as main container and
         * a bottom navigation view
         */
        fun activityBase(context: Context): ActivityBaseBinding {
            return ActivityBaseBinding.inflate(context.layoutInflater)
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

            val binding = PartialLoadingBinding.inflate(context.layoutInflater)

            binding.txtDescription.text = message
            binding.progressSpinner.setIndeterminateDrawable(drawable)

            return binding
        }

        /**
         * Text input layout with a text input edit text
         */
        fun formField(context: Context, label: String = "", text: MutableLiveData<String>): PartialFormFieldBinding {
            val binding = PartialFormFieldBinding.inflate(context.layoutInflater)

            binding.label = label
            binding.text = text

            return binding
        }

        /**
         * Text input layout with a masked edit text
         */
        fun formMaskedField(
                context: Context,
                label: String = "",
                mask: String = "",
                text: MutableLiveData<String>
        ): PartialFormMaskedFieldBinding {

            val binding = PartialFormMaskedFieldBinding.inflate(context.layoutInflater)

            binding.label = label
            binding.text = text
            binding.editField.mask = mask

            return binding
        }

        /**
         * Text input layout with hidden text used for passwords
         */
        fun formPasswordField(
                context: Context,
                label: String = "",
                text: MutableLiveData<String>
        ): PartialFormPasswordFieldBinding {

            val binding = PartialFormPasswordFieldBinding.inflate(context.layoutInflater)

            binding.label = label
            binding.text = text

            return binding
        }

        /**
         * Simple dialog with a yes and no button
         */
        fun yesNoDialog(context: Context,
                        title: String = "",
                        message: String = "",
                        @ColorInt titleColor: Int = ContextCompat.getColor(context, R.color.colorPrimary),
                        init: RConsumer<DialogYesNoBinding> = {}
        ): DialogYesNoBinding {
            val binding = DialogYesNoBinding.inflate(context.layoutInflater)

            binding.title = title
            binding.titleColor = titleColor
            binding.message = message
            binding.init()

            return binding
        }

    }
}