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
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.visuals.databinding.DialogYesNoBinding
import com.github.icarohs7.visuals.databinding.PartialFormFieldBinding
import com.github.icarohs7.visuals.databinding.PartialFormMaskedFieldBinding
import com.github.icarohs7.visuals.databinding.PartialFormPasswordFieldBinding
import com.github.icarohs7.visuals.view.dialogs.NXDialogBuilder
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.layoutInflater

interface UnoxAndroidVisualsModule {
    interface VisualsProvider {

        /**
         * Build and show a nxDialog
         */
        fun nxDialog(context: Context, fn: NXDialogBuilder.() -> Unit)
    }

    /**
     * Interface representing an element containing
     * a composite disposable
     */
    interface DisposableEntity {
        val disposable: CompositeDisposable
    }

    /**
     * Bindings for the layout templates
     */
    object NXBindings {

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
                        init: DialogYesNoBinding.() -> Unit = {}
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