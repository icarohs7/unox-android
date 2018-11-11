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

package com.github.icarohs7.library.extensions

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.library.UnoxAndroidSettings
import spencerstudios.com.bungeelib.Bungee
import kotlin.reflect.KClass

/**
 * Navigate from an activity to another
 */
fun <T : AppCompatActivity> Context.navigateTo(destination: KClass<T>) {
    val intent = Intent(this, destination.java)
    startActivity(intent)
    executeAnimation(this)
}

/**
 * Return the [Intent] used to launch the parameterized activity
 */
fun Context.getActivityLaunchIntent(activity: KClass<out AppCompatActivity>): Intent {
    return Intent(this, activity.java)
}

/**
 * Return the [PendingIntent] used to launch the parameterized activity
 */
fun Context.getPendingIntentToActivity(activityClass: KClass<out AppCompatActivity>): PendingIntent {
    val intent = getActivityLaunchIntent(activityClass)
    return PendingIntent.getActivity(this, 0, intent, 0)
}

/**
 * Execute an activity transition animation
 */
private fun executeAnimation(context: Context) {
    when (UnoxAndroidSettings.animationType) {

        UnoxAndroidSettings.AnimationType.SPLIT -> Bungee.split(context)

        UnoxAndroidSettings.AnimationType.SHRINK -> Bungee.shrink(context)

        UnoxAndroidSettings.AnimationType.CARD -> Bungee.card(context)

        UnoxAndroidSettings.AnimationType.INOUT -> Bungee.inAndOut(context)

        UnoxAndroidSettings.AnimationType.SWIPE_LEFT -> Bungee.swipeLeft(context)

        UnoxAndroidSettings.AnimationType.SWIPE_RIGHT -> Bungee.swipeRight(context)

        UnoxAndroidSettings.AnimationType.SLIDE_UP -> Bungee.slideUp(context)

        UnoxAndroidSettings.AnimationType.SLIDE_DOWN -> Bungee.slideDown(context)

        UnoxAndroidSettings.AnimationType.SLIDE_LEFT -> Bungee.slideLeft(context)

        UnoxAndroidSettings.AnimationType.SLIDE_RIGHT -> Bungee.slideRight(context)

        UnoxAndroidSettings.AnimationType.FADE -> Bungee.fade(context)

        UnoxAndroidSettings.AnimationType.ZOOM -> Bungee.zoom(context)

        UnoxAndroidSettings.AnimationType.WINDMILL -> Bungee.windmill(context)

        UnoxAndroidSettings.AnimationType.SPIN -> Bungee.spin(context)

        UnoxAndroidSettings.AnimationType.DIAGONAL -> Bungee.diagonal(context)

        UnoxAndroidSettings.AnimationType.NO_ANIMATION -> Unit
    }
}