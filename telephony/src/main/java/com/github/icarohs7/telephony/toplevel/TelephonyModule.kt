@file:JvmName("TelephonyModule")
@file:JvmMultifileClass
package com.github.icarohs7.telephony.toplevel

import com.github.icarohs7.telephony.providers.PhoneCallProvider
import com.github.icarohs7.telephony.providers.implementations.PhoneCallProviderImpl

fun getTelephonyProvider(): PhoneCallProvider = PhoneCallProviderImpl()