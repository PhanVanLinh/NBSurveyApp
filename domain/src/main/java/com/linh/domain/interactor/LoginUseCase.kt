package com.linh.domain.interactor

import com.linh.domain.base.Result
import com.linh.domain.repository.AuthRepository

class LoginUseCase(val authRepository: AuthRepository) : UseCase<LoginUseCase.Input, Unit>() {

    override suspend fun invoke(input: Input): Result<Unit> {
        // todo
        return Result.Success(Unit)
    }

    data class Input(
        val grantType: String,
        val email: String,
        val password: Int
    ) : UseCase.Input()
}