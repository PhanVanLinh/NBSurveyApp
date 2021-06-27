package com.linh.data.source.remote.api

import com.linh.data.source.remote.model.AccessTokenData
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NBSurveyNoneAuthApi {

    @Headers("Content-Type: application/json")
    @POST("api/v1/oauth/token")
    suspend fun login(@Body body: String): AccessTokenData
}