package com.linh.domain.interactor

import com.linh.domain.base.Result
import com.linh.domain.repository.SurveyRepository

class GetSurveyListUseCase(val surveyRepository: SurveyRepository) :
    UseCase<GetSurveyListUseCase.Input, Unit>() {

    override suspend fun invoke(input: Input): Result<Unit> {
        // todo
        return Result.Success(Unit)
    }

    data class Input(
        val pageNumber: Int,
        val pageSize: Int
    ) : UseCase.Input()
}