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

package com.github.icarohs7.unoxandroid.extensions.views

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.github.icarohs7.unoxandroid.UnoxAndroid
import com.github.icarohs7.unoxandroid.databinding.DialogYesNoBinding
import org.jetbrains.anko.layoutInflater
import spencerstudios.com.bungeelib.Bungee
import java.util.Calendar
import kotlin.reflect.KClass

/**
 * Navigate from an activity to another
 */
fun <T : AppCompatActivity> Context.navigateTo(
        destination: KClass<T>,
        extras: Bundle = bundleOf(),
        finishActivity: Boolean = false
) {
    val intent = Intent(this, destination.java)
    intent.putExtras(extras)
    startActivity(intent)
    if (this is Activity) {
        executeAnimation(this)
        if (UnoxAndroid.finishActivityOnNavigate || finishActivity) finish()
    }
}

/**
 * Execute an activity transition animation
 */
private fun executeAnimation(context: Context) {
    when (UnoxAndroid.animationType) {

        UnoxAndroid.AnimationType.SPLIT -> Bungee.split(context)

        UnoxAndroid.AnimationType.SHRINK -> Bungee.shrink(context)

        UnoxAndroid.AnimationType.CARD -> Bungee.card(context)

        UnoxAndroid.AnimationType.INOUT -> Bungee.inAndOut(context)

        UnoxAndroid.AnimationType.SWIPE_LEFT -> Bungee.swipeLeft(context)

        UnoxAndroid.AnimationType.SWIPE_RIGHT -> Bungee.swipeRight(context)

        UnoxAndroid.AnimationType.SLIDE_UP -> Bungee.slideUp(context)

        UnoxAndroid.AnimationType.SLIDE_DOWN -> Bungee.slideDown(context)

        UnoxAndroid.AnimationType.SLIDE_LEFT -> Bungee.slideLeft(context)

        UnoxAndroid.AnimationType.SLIDE_RIGHT -> Bungee.slideRight(context)

        UnoxAndroid.AnimationType.FADE -> Bungee.fade(context)

        UnoxAndroid.AnimationType.ZOOM -> Bungee.zoom(context)

        UnoxAndroid.AnimationType.WINDMILL -> Bungee.windmill(context)

        UnoxAndroid.AnimationType.SPIN -> Bungee.spin(context)

        UnoxAndroid.AnimationType.DIAGONAL -> Bungee.diagonal(context)

        UnoxAndroid.AnimationType.NO_ANIMATION -> Unit
    }
}

/**
 * Return a date picker dialog
 */
fun Context.dialogDatePicker(listener: (year: Int, month: Int, day: Int) -> Unit): DatePickerDialog {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    return DatePickerDialog(
            this,
            { _, y, m, d -> listener(y, m + 1, d) },
            year,
            month,
            day)
}

/**
 * Return a time picker dialog
 */
fun Context.dialogTimePicker(listener: (hour: Int, minute: Int) -> Unit): TimePickerDialog {
    val c = Calendar.getInstance()
    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)

    return TimePickerDialog(
            this,
            { _, h, m -> listener(h, m) },
            hour,
            minute,
            DateFormat.is24HourFormat(this))
}

/** Show a confirm dialog */
fun Context.showConfirmDialog(
        title: String = "",
        message: String = "",
        builder: DialogYesNoBinding.(DialogInterface) -> Unit
) {
    val binding = DialogYesNoBinding.inflate(layoutInflater)
    binding.title = title
    binding.message = message
    val dialog = binding.showAlert()
    binding.setNoHandler { dialog.dismiss() }
    binding.builder(dialog)
}

/** Show a confirm dialog */
fun Context.showConfirmDialog(
        title: String = "",
        message: String = "",
        yesHandler: View.OnClickListener
) {
    val binding = DialogYesNoBinding.inflate(layoutInflater)
    binding.title = title
    binding.message = message
    val dialog = binding.showAlert()
    binding.setNoHandler { dialog.dismiss() }
    binding.setYesHandler {
        yesHandler.onClick(it)
        dialog.dismiss()
    }
}