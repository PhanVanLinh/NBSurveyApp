package com.linh.data.source

import com.linh.domain.base.Result
import com.linh.domain.model.User
import com.linh.domain.repository.UserRepository
import com.linh.domain.repository.datasource.local.UserLocalDataSource
import com.linh.domain.repository.datasource.remote.UserRemoteDataSource
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getProfile(): Result<User> {
        TODO("Not yet implemented")
    }
}