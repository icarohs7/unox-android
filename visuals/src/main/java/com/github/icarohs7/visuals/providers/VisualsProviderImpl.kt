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

package com.github.icarohs7.visuals.providers

import android.content.Context
import com.github.icarohs7.core.typealiases.RConsumer
import com.github.icarohs7.visuals.VisualsModule
import com.github.icarohs7.visuals.databinding.DialogYesNoBinding
import com.github.icarohs7.visuals.extensions.toDialog
import com.github.icarohs7.visuals.view.dialogs.NXDialogBuilder

internal class VisualsProviderImpl : VisualsModule.VisualsProvider {
    /**
     * Build and show a nxDialog
     */
    override fun nxDialog(context: Context, fn: NXDialogBuilder.() -> Unit) {
        val builder = NXDialogBuilder(context)
        builder.fn()
        builder.build()
    }

    /**
     * Build and show a simple confirmation dialog
     */
    override fun yesNoDialog(
            context: Context,
            title: String,
            message: String,
            titleColor: Int,
            init: RConsumer<DialogYesNoBinding>
    ) {

        val binding = VisualsModule.NXBindings.yesNoDialog(context, title, message, titleColor)
        val dialog = binding.toDialog()
        binding.setNoHandler { dialog.dismiss() }
        binding.init()
        dialog.show()
    }
}