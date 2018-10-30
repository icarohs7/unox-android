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

package com.github.icarohs7.navigation.extensions

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.navigation.UnoxAndroidNavigationModule
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
    when (UnoxAndroidNavigationModule.animationType) {

        UnoxAndroidNavigationModule.AnimationType.SPLIT -> Bungee.split(context)

        UnoxAndroidNavigationModule.AnimationType.SHRINK -> Bungee.shrink(context)

        UnoxAndroidNavigationModule.AnimationType.CARD -> Bungee.card(context)

        UnoxAndroidNavigationModule.AnimationType.INOUT -> Bungee.inAndOut(context)

        UnoxAndroidNavigationModule.AnimationType.SWIPE_LEFT -> Bungee.swipeLeft(context)

        UnoxAndroidNavigationModule.AnimationType.SWIPE_RIGHT -> Bungee.swipeRight(context)

        UnoxAndroidNavigationModule.AnimationType.SLIDE_UP -> Bungee.slideUp(context)

        UnoxAndroidNavigationModule.AnimationType.SLIDE_DOWN -> Bungee.slideDown(context)

        UnoxAndroidNavigationModule.AnimationType.SLIDE_LEFT -> Bungee.slideLeft(context)

        UnoxAndroidNavigationModule.AnimationType.SLIDE_RIGHT -> Bungee.slideRight(context)

        UnoxAndroidNavigationModule.AnimationType.FADE -> Bungee.fade(context)

        UnoxAndroidNavigationModule.AnimationType.ZOOM -> Bungee.zoom(context)

        UnoxAndroidNavigationModule.AnimationType.WINDMILL -> Bungee.windmill(context)

        UnoxAndroidNavigationModule.AnimationType.SPIN -> Bungee.spin(context)

        UnoxAndroidNavigationModule.AnimationType.DIAGONAL -> Bungee.diagonal(context)

        UnoxAndroidNavigationModule.AnimationType.NO_ANIMATION -> Unit
    }
}