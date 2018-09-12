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

@file:JvmName("UserInterfaceModule")
@file:JvmMultifileClass

package com.github.icarohs7.userinterface.toplevel

import android.content.Context
import com.github.icarohs7.userinterface.providers.NavigationProvider
import com.github.icarohs7.userinterface.providers.NotificationProvider
import com.github.icarohs7.userinterface.providers.UIProvider
import com.github.icarohs7.userinterface.providers.implementations.NavigationProviderImpl
import com.github.icarohs7.userinterface.providers.implementations.NotificationProviderImpl
import com.github.icarohs7.userinterface.providers.implementations.UIProviderImpl

fun getUIProvider(): UIProvider = UIProviderImpl()

fun getNavigationProvider(context: Context): NavigationProvider = NavigationProviderImpl(context)

@JvmOverloads
fun getNotificationProvider(
        context: Context,
        channelId: String = "standardchannelid"
): NotificationProvider = NotificationProviderImpl(context, channelId)