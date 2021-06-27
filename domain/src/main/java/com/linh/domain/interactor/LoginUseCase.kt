package com.linh.domain.interactor

import com.linh.domain.base.Result
import com.linh.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) :
    UseCase<LoginUseCase.Input, Unit>() {

    override suspend fun invoke(input: Input): Result<Unit> {
        return authRepository.login(input.email, input.password)
    }

    data class Input(
        val email: String,
        val password: String
    ) : UseCase.Input()
}