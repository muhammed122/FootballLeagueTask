package com.example.footballleaguetask.utils

import androidx.test.core.app.ApplicationProvider
import com.example.utils.network.NetworkChecker
import javax.inject.Inject

class FakeNetworkChecker @Inject constructor() : NetworkChecker(ApplicationProvider.getApplicationContext()) {
    private var connected: Boolean = true

    override fun isConnected(): Boolean = connected
}