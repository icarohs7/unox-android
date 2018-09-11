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

package com.github.icarohs7.userinterface.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.github.icarohs7.userinterface.R
import kotlinx.android.synthetic.main.dialog_nx.button
import kotlinx.android.synthetic.main.dialog_nx.root_nx
import kotlinx.android.synthetic.main.dialog_nx.view.imageView
import kotlinx.android.synthetic.main.dialog_nx.view.textView
import kotlinx.android.synthetic.main.dialog_nx.view.textView2
import org.jetbrains.anko.backgroundColorResource

internal class NXDialog(
        context: Context,
        val title: String,
        val message: String,
        val icon: Drawable?,
        val backgroundColorResource: Int,
        val buttonCallback: Runnable,
        val dismissCallback: Runnable
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_nx)
        val root = root_nx
        root.imageView.setImageDrawable(icon)
        root.backgroundColorResource = backgroundColorResource
        root.textView.text = title
        root.textView2.text = message
        button.setOnClickListener {
            buttonCallback.run()
            dismiss()
        }
    }

    override fun dismiss() {
        super.dismiss()
        dismissCallback.run()
    }
}