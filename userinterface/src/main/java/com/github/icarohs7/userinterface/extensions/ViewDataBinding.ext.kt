package com.github.icarohs7.userinterface.extensions

import android.content.DialogInterface
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import org.jetbrains.anko.alert
import org.jetbrains.anko.customView
import org.jetbrains.anko.matchParent

/**
 * Convert a binding to a dialog and show it, returning the
 * dialog interface shown
 */
fun <T : ViewDataBinding> T.showAsDialog(): DialogInterface {
    val ctx = this.root.context
    return ctx.alert {
        customView {
            addView(this@showAsDialog.root, ViewGroup.LayoutParams(matchParent, matchParent))
        }
    }.show()
}