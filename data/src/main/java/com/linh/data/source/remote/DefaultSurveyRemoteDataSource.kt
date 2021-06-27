package com.linh.data.source.remote

import com.linh.data.source.remote.api.NBSurveyAuthApi
import com.linh.domain.repository.datasource.remote.SurveyRemoteDataSource
import javax.inject.Inject

class DefaultSurveyRemoteDataSource @Inject constructor(
    private val nbSurveyAuthApi: NBSurveyAuthApi
) : SurveyRemoteDataSource {

}