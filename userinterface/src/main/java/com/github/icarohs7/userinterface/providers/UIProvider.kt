package com.github.icarohs7.userinterface.providers

import android.content.Context
import com.github.icarohs7.userinterface.dialogs.NXDialogBuilder

interface UIProvider {
    fun nxDialog(context: Context, fn: NXDialogBuilder.() -> Unit)
}