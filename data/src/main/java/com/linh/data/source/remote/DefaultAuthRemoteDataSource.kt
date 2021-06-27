package com.linh.data.source.remote

import com.linh.data.source.remote.api.NBSurveyNoneAuthApi
import com.linh.domain.repository.datasource.remote.AuthRemoteDataSource
import javax.inject.Inject

class DefaultAuthRemoteDataSource @Inject constructor(
    private val nbSurveyNoneAuthApi: NBSurveyNoneAuthApi
) : AuthRemoteDataSource {

}