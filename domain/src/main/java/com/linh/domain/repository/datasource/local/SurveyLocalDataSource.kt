package com.linh.domain.repository.datasource.local

import com.linh.domain.model.Survey

interface SurveyLocalDataSource {
    suspend fun deleteAllSurvey()
    suspend fun saveListSurvey(surveys: List<Survey>)
    suspend fun getListSurvey(pageNumber: Int, pageSize: Int): List<Survey>
}