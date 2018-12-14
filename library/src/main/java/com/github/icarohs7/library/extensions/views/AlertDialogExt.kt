package com.github.icarohs7.library.extensions.views

import android.app.AlertDialog
import android.view.WindowManager

/**
 * Show the soft keyboard
 */
fun AlertDialog.showSoftKeyboard() {
    this.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}