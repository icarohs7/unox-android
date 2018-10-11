package com.github.icarohs7.visuals.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.ViewDataBinding
import org.jetbrains.anko.AlertBuilder
import org.jetbrains.anko.alert
import org.jetbrains.anko.customView
import org.jetbrains.anko.matchParent

/**
 * Convert a binding to a dialog and show it, returning the
 * dialog object shown
 */
fun <T : ViewDataBinding> T.toDialog(): Dialog {
    val dialog = Dialog(this.root.context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(this.root)
    return dialog
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

/**
 * Convert a binding to an alert and show it, returning the
 * dialog interface
 */
fun <T : ViewDataBinding> T.showAlert(): DialogInterface {
    return this.root.context.alert {
        customView {
            addView(this@showAlert.root, ViewGroup.LayoutParams(matchParent, matchParent))
        }
    }.show()
}

/**
 * Convert a binding to an alert builder
 */
fun <T : ViewDataBinding> T.toAlertBuilder(): AlertBuilder<DialogInterface> {
    return this.root.context.alert {
        customView {
            addView(this@toAlertBuilder.root, ViewGroup.LayoutParams(matchParent, matchParent))
        }
    }
}