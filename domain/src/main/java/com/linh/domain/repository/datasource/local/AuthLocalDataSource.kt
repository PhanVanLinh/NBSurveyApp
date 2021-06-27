package com.linh.domain.repository.datasource.local

import com.linh.domain.model.AccessToken

interface AuthLocalDataSource {

    suspend fun saveAccessToken(accessToken: AccessToken)
}