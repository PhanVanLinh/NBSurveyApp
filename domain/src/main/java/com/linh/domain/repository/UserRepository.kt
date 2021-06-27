package com.linh.domain.repository

import com.linh.domain.base.Result
import com.linh.domain.model.User

interface UserRepository {

    suspend fun getProfile(): Result<User>
}