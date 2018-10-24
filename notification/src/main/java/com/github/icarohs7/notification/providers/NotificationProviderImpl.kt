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

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.github.icarohs7.navigation.extensions.getPendingIntentToActivity
import com.github.icarohs7.notification.UnoxAndroidNotificationModule
import kotlin.reflect.KClass

internal class NotificationProviderImpl(
        private val context: Context,
        private val channelId: String = "standardnotificationchannel"
) : UnoxAndroidNotificationModule.NotificationProvider {

    private var notificationId = 0

    override fun emitNotification(
            title: String,
            message: String,
            iconResource: Int,
            bigMessage: String,
            onClickPendingIntent: PendingIntent
    ) {

        val builder = defaultBuilder(bigMessage)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(iconResource)
                .setContentIntent(onClickPendingIntent)

        emitNotification(builder)
    }

    override fun emitNotification(
            title: String,
            message: String,
            iconResource: Int,
            bigMessage: String,
            destinationActivity: KClass<out AppCompatActivity>
    ) {
        emitNotification(
                title,
                message,
                iconResource,
                bigMessage,
                context.getPendingIntentToActivity(destinationActivity))
    }

    override fun buildNotification(bigMessage: String, fn: NotificationCompat.Builder.() -> Unit) {
        val builder = defaultBuilder(bigMessage)
        builder.fn()
        emitNotification(builder)
    }

    private fun emitNotification(builder: NotificationCompat.Builder) {
        createNotificationChannel()
        val notification = builder.build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId++, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channelId
            val description = ""
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun defaultBuilder(bigMessage: String): NotificationCompat.Builder {
        val notificationStyle = NotificationCompat.BigTextStyle().bigText(bigMessage)

        return NotificationCompat
                .Builder(context, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setVibrate(longArrayOf(200, 1000))
                .setAutoCancel(true)
                .setStyle(notificationStyle)
    }
}