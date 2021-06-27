package com.linh.data.source.remote.middleware

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor() : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val newAccessToken = "temp" // todo
        return response.request().newBuilder()
            .header("Authorization", newAccessToken)
            .build()
    }
}