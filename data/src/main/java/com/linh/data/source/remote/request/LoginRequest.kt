package com.linh.data.source.remote.request

import com.squareup.moshi.Json

class LoginRequest(
    @Json(name = "grant_type")
    val grantType: String,
    val email: String,
    val password: String,
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "client_secret")
    val clientSecret: String
)