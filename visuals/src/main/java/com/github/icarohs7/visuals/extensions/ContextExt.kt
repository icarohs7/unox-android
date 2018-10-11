package com.github.icarohs7.visuals.extensions

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateFormat
import androidx.core.content.ContextCompat
import com.github.icarohs7.visuals.R
import com.github.icarohs7.visuals.VisualsModule
import com.github.icarohs7.visuals.databinding.DialogYesNoBinding
import java.util.Calendar

/**
 * Build and show a simple confirmation dialog
 */
fun Context.dialogYesNo(
        title: String,
        message: String,
        titleColor: Int = ContextCompat.getColor(this, R.color.colorPrimary),
        init: DialogYesNoBinding.(Dialog) -> Unit = {}
): Dialog {

    val binding = VisualsModule.NXBindings.yesNoDialog(this, title, message, titleColor)
    val dialog = binding.toDialog()
    binding.setNoHandler { dialog.dismiss() }
    init(binding, dialog)
    return dialog
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