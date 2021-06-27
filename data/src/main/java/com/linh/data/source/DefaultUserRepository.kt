package com.linh.data.source

import com.linh.domain.base.Result
import com.linh.domain.model.User
import com.linh.domain.repository.UserRepository
import com.linh.domain.repository.datasource.local.UserLocalDataSource
import com.linh.domain.repository.datasource.remote.UserRemoteDataSource

class DefaultUserRepository(
    val userLocalDataSource: UserLocalDataSource,
    val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getProfile(): Result<User> {
        TODO("Not yet implemented")
    }
}