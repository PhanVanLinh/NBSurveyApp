package com.linh.data.source

import com.linh.domain.base.Result
import com.linh.domain.model.Survey
import com.linh.domain.repository.SurveyRepository
import com.linh.domain.repository.datasource.local.SurveyLocalDataSource
import com.linh.domain.repository.datasource.remote.SurveyRemoteDataSource

class DefaultSurveyRepository(
    val surveyLocalDataSource: SurveyLocalDataSource,
    val surveyRemoteDataSource: SurveyRemoteDataSource,
) : SurveyRepository {

    override suspend fun getList(pageNumber: Int, pageSize: Int): Result<List<Survey>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetail(id: String): Result<Survey> {
        TODO("Not yet implemented")
    }
}