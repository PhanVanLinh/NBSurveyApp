package com.linh.domain.repository

import com.linh.domain.base.Result

internal interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Boolean>

    suspend fun logout(): Result<Boolean>
}