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

package com.github.icarohs7.library

import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface UnoxAndroid {

    /**
     * Interface representing an element containing
     * a composite disposable
     */
    interface DisposableEntity {
        val disposable: CompositeDisposable
    }

    /**
     * Companion object storing the settings of the module
     */
    companion object {

        /** Coroutine dispatcher that should be avoided for heavy work */
        var foregroundDispatcher: CoroutineDispatcher = Dispatchers.Main

        /**
         * Animation used at the transition between activities
         */
        var animationType: AnimationType = AnimationType.NO_ANIMATION

        /**
         * Container used to home the fragments loaded
         */
        var masterContainer: Int? = null

        /* Animations used on the animated fragment transitions */
        var enterAnim: Int = R.anim.zoom_enter      /* ------- */
        var exitAnim: Int = R.anim.zoom_exit        /* ------- */
        var popEnterAnim: Int = R.anim.zoom_enter   /* ------- */
        var popExitAnim: Int = R.anim.zoom_exit     /* ------- */
        /* --------------------------------------------------- */
    }

    /**
     * Animations available at the [animationType]
     */
    enum class AnimationType {
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