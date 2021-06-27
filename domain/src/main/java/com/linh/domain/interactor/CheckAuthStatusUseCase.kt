package com.linh.domain.interactor

import com.linh.domain.base.Result
import com.linh.domain.repository.AuthRepository

class CheckAuthStatusUseCase(
    private val authRepository: AuthRepository
) : UseCase<CheckAuthStatusUseCase.Input, Boolean>() {

    override suspend fun invoke(input: Input): Result<Boolean> {
        return authRepository.isLoggedIn()
    }

    object Input : UseCase.Input()
}