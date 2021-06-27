package com.linh.data.source

import com.linh.data.source.mapper.SurveyDataMapper
import com.linh.domain.base.Result
import com.linh.domain.model.Survey
import com.linh.domain.repository.SurveyRepository
import com.linh.domain.repository.datasource.local.SurveyLocalDataSource
import com.linh.domain.repository.datasource.remote.SurveyRemoteDataSource
import javax.inject.Inject

class DefaultSurveyRepository @Inject constructor(
    private val surveyLocalDataSource: SurveyLocalDataSource,
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
    private val surveyDataMapper: SurveyDataMapper
) : SurveyRepository {

    override suspend fun getList(
        pageNumber: Int,
        pageSize: Int,
        isCacheDirty: Boolean
    ): Result<List<Survey>> {
        if (isCacheDirty) {
            updateSurveysFromRemoteDataSource(pageNumber, pageSize)
        }
        return Result.Success(surveyLocalDataSource.getListSurvey(pageNumber, pageSize))
    }

    private suspend fun updateSurveysFromRemoteDataSource(pageNumber: Int, pageSize: Int) {
        val remoteProducts = surveyRemoteDataSource.getSurveyList(pageNumber,pageSize)

        if (remoteProducts is Result.Success) {
            surveyLocalDataSource.deleteAllSurvey()
            surveyLocalDataSource.saveListSurvey(remoteProducts.data)
        }
    }

    override suspend fun getDetail(id: String): Result<Survey> {
        TODO("Not yet implemented")
    }
}