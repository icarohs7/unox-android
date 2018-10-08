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

package com.github.icarohs7.navigation.providers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.navigation.NavigationModule
import spencerstudios.com.bungeelib.Bungee

internal class NavigationProviderImpl(private val context: Context) : NavigationModule.NavigationProvider {
    @Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")
    override inline fun <T : AppCompatActivity> gotoActivity(activity: Class<T>) {
        val intent = Intent(context, activity)
        context.startActivity(intent)
        executeAnimation(context)
    }

    override fun <T : AppCompatActivity> getActivityLaunchIntent(activity: Class<T>): Intent {
        return Intent(context, activity)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun executeAnimation(context: Context) {
        when (NavigationModule.animationType) {

            NavigationModule.AnimationType.SPLIT -> Bungee.split(context)

            NavigationModule.AnimationType.SHRINK -> Bungee.shrink(context)

            NavigationModule.AnimationType.CARD -> Bungee.card(context)

            NavigationModule.AnimationType.INOUT -> Bungee.inAndOut(context)

            NavigationModule.AnimationType.SWIPE_LEFT -> Bungee.swipeLeft(context)

            NavigationModule.AnimationType.SWIPE_RIGHT -> Bungee.swipeRight(context)

            NavigationModule.AnimationType.SLIDE_UP -> Bungee.slideUp(context)

            NavigationModule.AnimationType.SLIDE_DOWN -> Bungee.slideDown(context)

            NavigationModule.AnimationType.SLIDE_LEFT -> Bungee.slideLeft(context)

            NavigationModule.AnimationType.SLIDE_RIGHT -> Bungee.slideRight(context)

            NavigationModule.AnimationType.FADE -> Bungee.fade(context)

            NavigationModule.AnimationType.ZOOM -> Bungee.zoom(context)

            NavigationModule.AnimationType.WINDMILL -> Bungee.windmill(context)

            NavigationModule.AnimationType.SPIN -> Bungee.spin(context)

            NavigationModule.AnimationType.DIAGONAL -> Bungee.diagonal(context)

            NavigationModule.AnimationType.NO_ANIMATION -> Unit

        }
    }
}