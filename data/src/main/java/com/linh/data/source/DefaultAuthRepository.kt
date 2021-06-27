package com.linh.data.source

import com.linh.domain.base.Result
import com.linh.domain.repository.AuthRepository
import com.linh.domain.repository.datasource.local.AuthLocalDataSource
import com.linh.domain.repository.datasource.remote.AuthRemoteDataSource

class DefaultAuthRepository(
    val authLocalDataSource: AuthLocalDataSource,
    val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository{

    override suspend fun login(email: String, password: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}