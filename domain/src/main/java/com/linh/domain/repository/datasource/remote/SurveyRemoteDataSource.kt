package com.linh.domain.repository.datasource.remote

import com.linh.domain.model.Survey
import com.linh.domain.base.Result

interface SurveyRemoteDataSource {
    suspend fun getSurveyList(pageNumber: Int, pageSize: Int): Result<List<Survey>>
}