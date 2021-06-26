package com.linh.data.source.remote.request

class ForgotPasswordRequest(
    val client_id: String,
    val client_secret: String
) {

    class User(val email: String)
}