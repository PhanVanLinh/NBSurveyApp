package com.linh.data.source.remote.api

import com.linh.data.source.remote.model.SurveyData
import com.linh.data.source.remote.model.UserData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NBSurveyAuthApi {

    @GET("/api/v1/surveys")
    suspend fun getSurveyList(
        @Query("page[number]") pageNumber: Int,
        @Query("page[size]") pageSize: Int
    ): List<SurveyData>

    @GET("/api/v1/surveys/{id}")
    suspend fun getSurveyDetail(@Path("id") id: String): SurveyData

    @POST("/api/v1/passwords")
    suspend fun forgotPassword(): Boolean

    @GET("/api/v1/me")
    suspend fun getProfile(): UserData
}