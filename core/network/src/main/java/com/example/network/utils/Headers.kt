package com.example.network.utils

object Headers {

    object Keys{
        const val AUTHORIZATION ="X-Auth-Token"
        const val ACCEPT ="accept"
        const val CONTENT_TYPE ="Content-Type"
        const val ACCEPT_LANGUAGE ="X-Localization"
        const val APP_SECRET = "App-Secret"
    }

    object Values {
        const val ACCEPT_VALUE ="application/json"
        const val BEARER ="Bearer "
    }
}