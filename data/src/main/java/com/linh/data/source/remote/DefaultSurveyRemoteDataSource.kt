package com.linh.data.source.remote

import com.linh.data.di.qualifier.IODispatcher
import com.linh.data.source.mapper.SurveyDataMapper
import com.linh.data.source.remote.api.NBSurveyAuthApi
import com.linh.domain.base.Result
import com.linh.domain.model.Survey
import com.linh.domain.repository.datasource.remote.SurveyRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultSurveyRemoteDataSource @Inject constructor(
    private val nbSurveyAuthApi: NBSurveyAuthApi,
    private val surveyDataMapper: SurveyDataMapper,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher,
) : BaseRemoteDataSource(), SurveyRemoteDataSource {

    override suspend fun getSurveyList(pageNumber: Int, pageSize: Int): Result<List<Survey>> {
        return withContext(dispatcher) {
            getResult {
                nbSurveyAuthApi.getSurveyList(pageNumber, pageSize).map {
                    surveyDataMapper.surveyDataToSurvey(it)
                }
            }
        }

    }
}