package com.github.icarohs7.versioncontrol.providers.implementations

import com.github.icarohs7.core.toplevel.onBgNoReturn
import com.github.icarohs7.versioncontrol.providers.abstractions.VersionControlProvider
import com.github.icarohs7.versioncontrol.versionControlProvider
import org.junit.Before
import org.junit.Test
import se.lovef.assert.v1.shouldBeFalse
import se.lovef.assert.v1.shouldBeTrue
import se.lovef.assert.v1.shouldEqual

class VersionControlProviderImplTest {
    private lateinit var provider: VersionControlProvider

    @Before
    fun setUp() {
        provider = versionControlProvider()
        provider.localVersionProvider = { "1.0.0" }
        provider.remoteVersionProvider = { "1.0.1" }
    }

    @Test
    fun `remote version is newer`() {
        onBgNoReturn {
            val metadata = provider.compareVersions().await()
            metadata.oldVersion shouldEqual "1.0.0"
            metadata.newVersion shouldEqual "1.0.1"
            metadata.isAppUpdated.shouldBeTrue()
        }
    }

    @Test
    fun `remote version is the same`() {
        onBgNoReturn {
            provider.localVersionProvider = { "0.0.1" }
            provider.remoteVersionProvider = { "0.0.1" }
            val metadata = provider.compareVersions().await()
            metadata.oldVersion shouldEqual "0.0.1"
            metadata.newVersion shouldEqual "0.0.1"
            metadata.isAppUpdated.shouldBeFalse()
        }
    }
}