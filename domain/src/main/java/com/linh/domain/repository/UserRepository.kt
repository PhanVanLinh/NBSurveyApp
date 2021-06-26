package com.linh.domain.repository

import com.linh.domain.base.Result
import com.linh.domain.model.User

internal interface UserRepository {

    suspend fun getProfile(): Result<User>
}