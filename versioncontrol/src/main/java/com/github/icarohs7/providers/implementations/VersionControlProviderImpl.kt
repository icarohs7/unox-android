package com.github.icarohs7.providers.implementations

import android.util.Log
import com.github.icarohs7.connectivity.callbacks.HttpOnSuccessListener
import com.github.icarohs7.entities.VersionMetadata
import com.github.icarohs7.providers.VersionControlProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

internal object VersionControlProviderImpl : VersionControlProvider {
    override var installedVersionProvider: (suspend () -> String)? = null
    override var remoteVersionProvider: (suspend () -> String)? = null

    override fun compareVersions(callback: HttpOnSuccessListener<VersionMetadata>) {
        installedVersionProvider ?: fail()
        remoteVersionProvider ?: fail()

        launch(UI) {
            val actual = installedVersionProvider!!()
            val remote = remoteVersionProvider!!()
            val outdated = remote isMoreRecentThan actual

            Log.i("versions", "actual: $actual, latest: $remote")
            callback.onSuccess(VersionMetadata(
                    oldVersion = actual,
                    newVersion = remote,
                    isAppUpdated = !outdated))
        }
    }

    private infix fun String.isMoreRecentThan(older: String): Boolean {
        if (this == older) return false

        try {
            val (newMajor, newMinor, newPatch) = this.split(".")
            val (oldMajor, oldMinor, oldPatch) = older.split(".")

            if (newPatch > oldPatch) return true
            if (newMinor > oldMinor) return true
            if (newMajor > oldMajor) return true

        } catch (e: IndexOutOfBoundsException) {
            Log.e("version_error", "Please use the format major.minor.patch on your version. $e")
            return false
        }

        return false
    }

    private fun fail() {
        throw Exception("Please define the version providers for local and remote versions")
    }
}