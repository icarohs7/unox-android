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

package com.github.icarohs7.versioncontrol.providers.implementations

import android.util.Log
import com.github.icarohs7.core.toplevel.onBg
import com.github.icarohs7.versioncontrol.entities.VersionMetadata
import com.github.icarohs7.versioncontrol.providers.abstractions.VersionControlProvider
import kotlinx.coroutines.experimental.Deferred

internal object VersionControlProviderImpl : VersionControlProvider {
    override lateinit var localVersionProvider: (suspend () -> String)
    override lateinit var remoteVersionProvider: (suspend () -> String)

    override fun compareVersions(): Deferred<VersionMetadata> {
        return onBg { _ ->
            val actual = localVersionProvider()
            val remote = remoteVersionProvider()
            val outdated = remote isMoreRecentThan actual

            Log.i("versions", "actual: $actual, latest: $remote")
            VersionMetadata(
                    oldVersion = actual,
                    newVersion = remote,
                    isAppUpdated = !outdated)
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
}