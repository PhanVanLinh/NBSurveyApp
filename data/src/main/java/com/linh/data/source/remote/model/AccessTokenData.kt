package com.linh.data.source.remote.model

class AccessTokenData(
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val refresh_token: String,
    val created_at: Long
)