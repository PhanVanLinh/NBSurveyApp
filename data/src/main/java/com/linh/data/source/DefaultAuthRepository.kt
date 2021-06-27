package com.linh.data.source

import com.linh.domain.base.Result
import com.linh.domain.repository.AuthRepository
import com.linh.domain.repository.datasource.local.AuthLocalDataSource
import com.linh.domain.repository.datasource.remote.AuthRemoteDataSource
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        val loginResult = authRemoteDataSource.login(email, password)
        return if (loginResult is Result.Success) {
            authLocalDataSource.saveAccessToken(loginResult.data)
            Result.Success(Unit)
        } else {
            loginResult.errorMap()
        }
    }

    override suspend fun logout(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}