@file:JvmName("UserInterfaceModule")
@file:JvmMultifileClass

package com.github.icarohs7.userinterface.toplevel

import com.github.icarohs7.userinterface.providers.UIProvider
import com.github.icarohs7.userinterface.providers.implementations.UIProviderImpl

fun getUIProvider(): UIProvider = UIProviderImpl()