package com.github.icarohs7.visuals.extensions

import android.app.AlertDialog
import androidx.databinding.ViewDataBinding

/**
 * Convert a binding to a dialog and show it, returning the
 * dialog object shown
 */
fun <T : ViewDataBinding> T.toDialog(): AlertDialog {
    return AlertDialog
            .Builder(this.root.context)
            .setView(this.root)
            .create()
}

/**
 * Convert a binding to a dialog builder with the root
 * view set as the dialog's content
 */
fun <T : ViewDataBinding> T.toDialogBuilder(): AlertDialog.Builder {
    return AlertDialog
            .Builder(this.root.context)
            .setView(this.root)
}