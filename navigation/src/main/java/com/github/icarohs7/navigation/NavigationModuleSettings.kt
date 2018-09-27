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

package com.github.icarohs7.navigation

import com.github.icarohs7.core.settings.UnoxAndroidSettings

fun UnoxAndroidSettings.startNavigationModule(builder: NavigationModuleSettings.() -> Unit) {
    builder(NavigationModuleSettings)
}

object NavigationModuleSettings {
    var animationType: Animation = Animation.NO_ANIMATION

    var masterContainer: Int? = null
    var activityContainer: MutableMap<String, Int> = mutableMapOf()

    var enterAnim = R.anim.zoom_enter
    var exitAnim = R.anim.zoom_exit
    var popEnterAnim = R.anim.zoom_enter
    var popExitAnim = R.anim.zoom_exit

    enum class Animation {
        SPLIT,
        SHRINK,
        CARD,
        INOUT,
        SWIPE_LEFT,
        SWIPE_RIGHT,
        SLIDE_UP,
        SLIDE_DOWN,
        SLIDE_LEFT,
        SLIDE_RIGHT,
        FADE,
        ZOOM,
        WINDMILL,
        SPIN,
        DIAGONAL,
        NO_ANIMATION;
    }
}