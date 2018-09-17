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

package com.github.icarohs7.notification.providers

import android.app.PendingIntent
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import com.github.icarohs7.notification.callbacks.NotificationBuilderConsumer

interface NotificationProvider {
    fun emitNotification(
            title: String,
            message: String,
            iconResource: Int,
            bigMessage: String,
            onClickPendingIntent: PendingIntent
    )

    fun buildNotification(bigMessage: String = "", fn: NotificationCompat.Builder.() -> Unit)

    fun <T : AppCompatActivity> emitNotification(
            title: String,
            message: String,
            iconResource: Int,
            bigMessage: String,
            destinationActivity: Class<T>
    )

    fun buildNotification(bigMessage: String, fn: NotificationBuilderConsumer): Unit =
            buildNotification(bigMessage) { fn.accept(this) }
}