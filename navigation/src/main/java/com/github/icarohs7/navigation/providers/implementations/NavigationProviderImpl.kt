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

package com.github.icarohs7.navigation.providers.implementations

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.github.icarohs7.navigation.NavigationModuleSettings
import com.github.icarohs7.navigation.providers.NavigationProvider
import spencerstudios.com.bungeelib.Bungee

internal class NavigationProviderImpl(private val context: Context) : NavigationProvider {
    override fun <T : AppCompatActivity> gotoActivity(activity: Class<T>) {
        val intent = Intent(context, activity)
        context.startActivity(intent)
        executeAnimation(context)
    }

    override fun <T : AppCompatActivity> getActivityLaunchIntent(activity: Class<T>): Intent {
        return Intent(context, activity)
    }

    private fun executeAnimation(context: Context) {
        when (NavigationModuleSettings.animationType) {

            NavigationModuleSettings.Animation.SPLIT -> Bungee.split(context)

            NavigationModuleSettings.Animation.SHRINK -> Bungee.shrink(context)

            NavigationModuleSettings.Animation.CARD -> Bungee.card(context)

            NavigationModuleSettings.Animation.INOUT -> Bungee.inAndOut(context)

            NavigationModuleSettings.Animation.SWIPE_LEFT -> Bungee.swipeLeft(context)

            NavigationModuleSettings.Animation.SWIPE_RIGHT -> Bungee.swipeRight(context)

            NavigationModuleSettings.Animation.SLIDE_UP -> Bungee.slideUp(context)

            NavigationModuleSettings.Animation.SLIDE_DOWN -> Bungee.slideDown(context)

            NavigationModuleSettings.Animation.SLIDE_LEFT -> Bungee.slideLeft(context)

            NavigationModuleSettings.Animation.SLIDE_RIGHT -> Bungee.slideRight(context)

            NavigationModuleSettings.Animation.FADE -> Bungee.fade(context)

            NavigationModuleSettings.Animation.ZOOM -> Bungee.zoom(context)

            NavigationModuleSettings.Animation.WINDMILL -> Bungee.windmill(context)

            NavigationModuleSettings.Animation.SPIN -> Bungee.spin(context)

            NavigationModuleSettings.Animation.DIAGONAL -> Bungee.diagonal(context)

            NavigationModuleSettings.Animation.NO_ANIMATION -> Unit

        }
    }
}