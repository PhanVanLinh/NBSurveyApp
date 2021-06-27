package com.linh.data.source.remote.request

import com.squareup.moshi.Json

class RefreshTokenRequest(
    @Json(name = "grant_type")
    val grantType: String,
    @Json(name = "refresh_token")
    val refreshToken: String,
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "client_secret")
    val clientSecret: String
)