package com.linh.domain.repository

import com.linh.domain.base.Result
import com.linh.domain.model.Survey

interface SurveyRepository {

    suspend fun getList(
        pageNumber: Int,
        pageSize: Int
    ): Result<List<Survey>>

    suspend fun getDetail(
        id: String
    ): Result<Survey>
}