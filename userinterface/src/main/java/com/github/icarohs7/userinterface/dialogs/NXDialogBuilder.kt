package com.github.icarohs7.userinterface.dialogs

import android.content.Context
import android.graphics.drawable.Drawable

class NXDialogBuilder(private val context: Context) {
    lateinit var title: String
    lateinit var message: String
    var icon: Drawable? = null
    var backgroundResource = android.R.color.white
    var buttonCallback = Runnable {}
    var dismissCallback = Runnable {}

    fun build() {
        NXDialog(
                context = context,
                title = title,
                message = message,
                icon = icon,
                backgroundColorResource = backgroundResource,
                buttonCallback = buttonCallback,
                dismissCallback = dismissCallback
        ).show()
    }
}