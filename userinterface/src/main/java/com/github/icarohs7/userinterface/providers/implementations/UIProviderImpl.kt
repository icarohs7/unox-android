package com.github.icarohs7.userinterface.providers.implementations

import android.content.Context
import com.github.icarohs7.userinterface.dialogs.NXDialogBuilder
import com.github.icarohs7.userinterface.providers.UIProvider

internal class UIProviderImpl : UIProvider {
    override fun nxDialog(context: Context, fn: NXDialogBuilder.() -> Unit) {
        val builder = NXDialogBuilder(context)
        builder.fn()
        builder.build()
    }
}