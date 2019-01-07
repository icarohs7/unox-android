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

package com.github.icarohs7.unoxandroid

import androidx.annotation.AnimRes
import com.github.icarohs7.unoxandroid.delegates.mutableLazy
import com.github.icarohs7.unoxandroid.domain.BungeeAnim
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface UnoxAndroid {
    /**
     * Companion object storing the settings of the module
     */
    companion object {

        /** Coroutine dispatcher that should be avoided for heavy work */
        var foregroundDispatcher: CoroutineDispatcher by mutableLazy { Dispatchers.Main }

        /** Define the animation used in the navigation transitions used by library */
        fun setActivityAndFragmentTransitionAnimation(animationType: AnimationType) {
            this.animationType = animationType
            enterAnim = animationType.enterRes
            exitAnim = animationType.exitRes
            popEnterAnim = animationType.enterRes
            popExitAnim = animationType.exitRes
        }

        /**
         * Animation used at the transition between activities
         */
        var animationType: AnimationType by mutableLazy { AnimationType.NO_ANIMATION }

        /**
         * Container used to home the fragments loaded
         */
        var masterContainer: Int by mutableLazy { R.id.container }

        /* Animations used on the animated fragment transitions -------------- */
        var enterAnim: Int by mutableLazy { R.anim.zoom_enter }     /* ------- */
        var exitAnim: Int by mutableLazy { R.anim.zoom_exit }       /* ------- */
        var popEnterAnim: Int by mutableLazy { R.anim.zoom_enter }  /* ------- */
        var popExitAnim: Int by mutableLazy { R.anim.zoom_exit }    /* ------- */
        /* ------------------------------------------------------------------- */

        /**
         * When set to true, every call to navigateTo from
         * an activity will also finish it after the navigation
         */
        var finishActivityOnNavigate: Boolean by mutableLazy { false }

        /**
         * When set to true, will allow a fragment to be loaded twice
         * in succession
         */
        var allowLoadingFragmentTwiceInARow: Boolean by mutableLazy { true }
    }

    /**
     * Animations available at the [animationType]
     */
    enum class AnimationType(@AnimRes val enterRes: Int, @AnimRes val exitRes: Int) {
        SPLIT(BungeeAnim.split_enter, BungeeAnim.split_exit),

        SHRINK(BungeeAnim.shrink_enter, BungeeAnim.shrink_exit),

        CARD(BungeeAnim.card_enter, BungeeAnim.card_exit),

        INOUT(BungeeAnim.in_out_enter, BungeeAnim.in_out_exit),

        SWIPE_LEFT(BungeeAnim.swipe_left_enter, BungeeAnim.swipe_left_exit),

        SWIPE_RIGHT(BungeeAnim.swipe_right_enter, BungeeAnim.swipe_right_exit),

        SLIDE_UP(BungeeAnim.slide_up_enter, BungeeAnim.slide_up_exit),

        SLIDE_DOWN(BungeeAnim.slide_down_enter, BungeeAnim.slide_down_exit),

        SLIDE_LEFT(BungeeAnim.slide_left_enter, BungeeAnim.slide_left_exit),

        SLIDE_RIGHT(BungeeAnim.slide_in_left, BungeeAnim.slide_out_right),

        FADE(BungeeAnim.fade_enter, BungeeAnim.fade_exit),

        ZOOM(BungeeAnim.zoom_enter, BungeeAnim.zoom_exit),

        WINDMILL(BungeeAnim.windmill_enter, BungeeAnim.windmill_exit),

        SPIN(BungeeAnim.spin_enter, BungeeAnim.spin_exit),

        DIAGONAL(BungeeAnim.diagonal_right_enter, BungeeAnim.diagonal_right_exit),

        NO_ANIMATION(0, 0);
    }
}