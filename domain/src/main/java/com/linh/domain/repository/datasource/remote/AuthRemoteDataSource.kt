package com.linh.domain.repository.datasource.remote

import com.linh.domain.base.Result
import com.linh.domain.model.AccessToken

interface AuthRemoteDataSource {

    suspend fun login(email: String, password: String): Result<AccessToken>

    suspend fun refreshToken(refreshToken: String): Result<AccessToken>
}