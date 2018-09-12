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

package com.github.icarohs7.userinterface.providers.implementations

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import com.github.icarohs7.userinterface.providers.NotificationProvider
import com.github.icarohs7.userinterface.toplevel.getNavigationProvider

/**
 * @author Icaro Temponi
 * @since 09/09/2018
 */
internal class NotificationProviderImpl(
        private val context: Context,
        private val channelId: String = "standardnotificationchannel"
) : NotificationProvider {

    private var notificationId = 0
    private val builder: NotificationCompat.Builder by lazy {
        NotificationCompat
                .Builder(context, channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setVibrate(longArrayOf(200, 1000))
                .setAutoCancel(true)
    }

    override fun emitNotification(
            title: String,
            message: String,
            iconResource: Int,
            bigMessage: String,
            destinationActivity: Class<AppCompatActivity>
    ) {
        createNotificationChannel()
        val notificationStyle = NotificationCompat.BigTextStyle().bigText(bigMessage)
        val pendingIntent = getPendingIntentToActivity(destinationActivity)

        val notification = builder
                .setSmallIcon(iconResource)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(notificationStyle)
                .setContentIntent(pendingIntent)
                .build()

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

    private fun getPendingIntentToActivity(activityClass: Class<out AppCompatActivity>): PendingIntent {
        val intent = getNavigationProvider(context).getActivityLaunchIntent(activityClass)
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}