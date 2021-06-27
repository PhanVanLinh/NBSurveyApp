package com.linh.domain.repository

import com.linh.domain.base.Result

interface AuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun logout(): Result<Boolean>
}