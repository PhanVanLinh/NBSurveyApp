package com.linh.domain.interactor

import com.linh.domain.base.Result
import com.linh.domain.model.Survey
import com.linh.domain.repository.SurveyRepository

class GetSurveyListUseCase(private val surveyRepository: SurveyRepository) :
    UseCase<GetSurveyListUseCase.Input, List<Survey>>() {

    override suspend fun invoke(input: Input): Result<List<Survey>> {
        return surveyRepository.getList(input.pageNumber, input.pageSize, input.isCacheDirty)
    }

    data class Input(
        val pageNumber: Int,
        val pageSize: Int,
        val isCacheDirty: Boolean
    ) : UseCase.Input()
}