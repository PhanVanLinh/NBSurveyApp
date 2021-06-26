package com.linh.data.source.remote

import com.linh.data.source.remote.model.AccessTokenData
import retrofit2.http.POST

interface NBSurveyNoneAuthApi {

    @POST("api/v1/oauth/token")
    suspend fun login(): AccessTokenData
}