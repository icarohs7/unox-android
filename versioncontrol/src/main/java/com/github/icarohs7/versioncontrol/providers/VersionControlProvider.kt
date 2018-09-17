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

package com.github.icarohs7.versioncontrol.providers

import com.github.icarohs7.connectivity.callbacks.HttpOnSuccessListener
import com.github.icarohs7.versioncontrol.entities.VersionMetadata
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface VersionControlProvider {
    var localVersionProvider: (suspend () -> String)?
    var remoteVersionProvider: (suspend () -> String)?

    //Kotlin Versions
    fun compareVersions(callback: (VersionMetadata) -> Unit)

    fun localVersion(callback: (String) -> Unit) {
        launch(UI) { callback(localVersionProvider?.invoke() ?: "") }
    }

    fun remoteVersion(callback: (String) -> Unit) {
        launch(UI) { callback(remoteVersionProvider?.invoke() ?: "") }
    }

    //Java Versions
    fun compareVersions(callback: HttpOnSuccessListener<VersionMetadata>) =
            compareVersions { callback.onSuccess(it) }

    fun localVersion(callback: HttpOnSuccessListener<String>) =
            localVersion { callback.onSuccess(it) }

    fun remoteVersion(callback: HttpOnSuccessListener<String>) =
            remoteVersion { callback.onSuccess(it) }
}