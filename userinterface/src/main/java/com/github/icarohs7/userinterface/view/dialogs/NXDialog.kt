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

package com.github.icarohs7.userinterface.view.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.plusAssign
import com.github.icarohs7.userinterface.R
import kotlinx.android.synthetic.main.dialog_nx.button
import kotlinx.android.synthetic.main.dialog_nx.imageView
import kotlinx.android.synthetic.main.dialog_nx.root_nx
import kotlinx.android.synthetic.main.dialog_nx.textView
import kotlinx.android.synthetic.main.dialog_nx.textView2
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.textColorResource

internal class NXDialog(
        context: Context,
        val title: String,
        val message: String,
        val icon: Drawable?,
        val backgroundColorResource: Int,
        val buttonText: String,
        val buttonTextColorResource: Int,
        val buttonColorResource: Int,
        val buttonCallback: Runnable,
        val dismissCallback: Runnable,
        val customView: View?
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.dialog_nx)
        if (customView != null) {
            root_nx.removeAllViews()
            root_nx += customView
        } else {
            textView.text = title
            textView2.text = message
            imageView.setImageDrawable(icon)
            root_nx.backgroundColorResource = backgroundColorResource
            button.text = buttonText
            button.textColorResource = buttonTextColorResource
            button.backgroundColorResource = buttonColorResource
            button.setOnClickListener {
                buttonCallback.run()
                dismiss()
            }
        }
    }

    override fun dismiss() {
        super.dismiss()
        dismissCallback.run()
    }
}