package com.github.icarohs7.telephony.providers

import android.content.Context

interface PhoneCallProvider {
    fun callNumber(phoneNumber: String, askingMessage: String, onDenyMessage: String, context: Context)
}