package com.linh.data.source.local

import com.linh.data.di.qualifier.IODispatcher
import com.linh.data.source.local.api.SurveyDao
import com.linh.data.source.mapper.SurveyDataMapper
import com.linh.domain.model.Survey
import com.linh.domain.repository.datasource.local.SurveyLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultSurveyLocalDataSource @Inject constructor(
    private val surveyDao: SurveyDao,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher,
    private val surveyDataMapper: SurveyDataMapper
) : SurveyLocalDataSource {

    override suspend fun deleteAllSurvey() = withContext(dispatcher) {
        surveyDao.nukeTable()
    }

    override suspend fun saveListSurvey(surveys: List<Survey>) = withContext(dispatcher) {
        surveyDao.insertAll(surveys.map { surveyDataMapper.surveyToSurveyEntity(it) })
    }

    override suspend fun getListSurvey(pageNumber: Int, pageSize: Int): List<Survey> {
        return withContext(dispatcher) {
            surveyDao.getAll().map { surveyDataMapper.surveyEntityToSurvey(it) }
        }
    }

}