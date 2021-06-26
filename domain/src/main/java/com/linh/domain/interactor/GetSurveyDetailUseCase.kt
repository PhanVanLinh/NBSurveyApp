package com.linh.domain.interactor

import com.linh.domain.base.Result

class GetSurveyDetailUseCase : UseCase<GetSurveyDetailUseCase.Input, Unit>() {

    override suspend fun invoke(input: Input): Result<Unit> {
        // todo
        return Result.Success(Unit)
    }

    data class Input(
        val surveyId: Int
    ) : UseCase.Input()
}