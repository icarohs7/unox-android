@file:JvmName("VersionControlName")
@file:JvmMultifileClass

package com.github.icarohs7.versioncontrol.toplevel

import com.github.icarohs7.providers.VersionControlProvider
import com.github.icarohs7.providers.implementations.VersionControlProviderImpl

fun getVersionControlProvider(): VersionControlProvider = VersionControlProviderImpl