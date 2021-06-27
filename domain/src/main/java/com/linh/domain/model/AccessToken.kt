package com.linh.domain.model

class AccessToken(
    val accessToken: String?,
    val tokenType: String?,
    val expiresIn: Long?,
    val refreshToken: String?,
    val createdAt: Long?
)