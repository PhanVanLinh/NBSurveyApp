package com.linh.data.source.mapper

import com.linh.data.source.remote.model.AccessTokenData
import com.linh.domain.model.AccessToken
import javax.inject.Inject

class AccessTokenDataMapper @Inject constructor() {

    fun accessTokenToAccessTokenData(f: AccessToken): AccessTokenData {
        return AccessTokenData().apply {
            accessToken = f.accessToken
            tokenType = f.tokenType
            expiresIn = f.expiresIn
            refreshToken = f.refreshToken
            createdAt = f.createdAt
        }
    }

    fun accessTokenDataToAccessToken(f: AccessTokenData): AccessToken {
        return AccessToken(
            accessToken = f.accessToken,
            tokenType = f.tokenType,
            expiresIn = f.expiresIn,
            refreshToken = f.refreshToken,
            createdAt = f.createdAt
        )
    }
}