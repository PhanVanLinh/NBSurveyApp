package com.linh.data.source.remote.request

class LoginRequest(
    val grantType: String,
    val email: String,
    val password: String,
    val client_id: String,
    val client_secret: String
)