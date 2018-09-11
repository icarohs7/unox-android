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