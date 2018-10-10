package com.github.icarohs7.visuals.extensions

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.github.icarohs7.core.typealiases.RConsumer
import com.github.icarohs7.visuals.R
import com.github.icarohs7.visuals.VisualsModule
import com.github.icarohs7.visuals.databinding.DialogYesNoBinding

/**
 * Build and show a simple confirmation dialog
 */
fun Context.yesNoDialog(context: Context,
                        title: String = "",
                        message: String = "",
                        @ColorInt titleColor: Int = ContextCompat.getColor(context, R.color.colorPrimary),
                        init: RConsumer<DialogYesNoBinding> = {}) =
        VisualsModule.VisualsProvider.get().yesNoDialog(context, title, message, titleColor, init)