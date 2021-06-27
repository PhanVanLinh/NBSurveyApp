package com.linh.domain.interactor

import com.linh.domain.base.Result
import com.linh.domain.repository.UserRepository

class GetProfileUseCase(
    val userRepository: UserRepository
) : UseCase<GetProfileUseCase.Input, Unit>() {

    override suspend fun invoke(input: Input): Result<Unit> {
        // todo
        return Result.Success(Unit)
    }

    object Input : UseCase.Input()
}