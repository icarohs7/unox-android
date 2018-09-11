package com.github.icarohs7.providers

import com.github.icarohs7.connectivity.callbacks.HttpOnSuccessListener
import com.github.icarohs7.entities.VersionMetadata

interface VersionControlProvider {
    var installedVersionProvider: (suspend () -> String)?
    var remoteVersionProvider: (suspend () -> String)?
    fun compareVersions(callback: HttpOnSuccessListener<VersionMetadata>)
}