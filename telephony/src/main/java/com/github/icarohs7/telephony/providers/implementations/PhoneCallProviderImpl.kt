package com.github.icarohs7.telephony.providers.implementations

import android.Manifest
import android.Manifest.permission
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import com.github.icarohs7.telephony.providers.PhoneCallProvider
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import org.jetbrains.anko.longToast
import java.util.ArrayList

internal class PhoneCallProviderImpl : PhoneCallProvider {

    override fun callNumber(phoneNumber: String, askingMessage: String, onDenyMessage: String, context: Context) {
        val callback = object : PermissionHandler() {
            override fun onGranted() {
                makeCall(phoneNumber, context)
            }

            override fun onDenied(context: Context, deniedPermissions: ArrayList<String>) {
                context.longToast(onDenyMessage)
            }
        }

        Permissions.check(context, Manifest.permission.CALL_PHONE, askingMessage, callback)
    }

    private fun makeCall(phoneNumber: String, context: Context) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")

        if (
                ActivityCompat.checkSelfPermission(context, permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            context.startActivity(callIntent)
        }
    }
}
