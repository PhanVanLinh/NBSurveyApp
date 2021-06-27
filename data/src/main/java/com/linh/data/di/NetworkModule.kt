package com.linh.data.di

import com.linh.data.BuildConfig
import com.linh.data.source.remote.api.NBSurveyAuthApi
import com.linh.data.source.remote.api.NBSurveyNoneAuthApi
import com.linh.data.source.remote.middleware.AuthInterceptor
import com.linh.data.source.remote.middleware.NoneAuthInterceptor
import com.linh.data.source.remote.middleware.TokenAuthenticator
import com.linh.data.source.remote.model.AccessTokenData
import com.linh.data.source.remote.model.AnswerData
import com.linh.data.source.remote.model.QuestionData
import com.linh.data.source.remote.model.SurveyData
import com.linh.data.source.remote.request.LoginRequest
import com.linh.data.source.remote.service.ServiceGenerator
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import moe.banana.jsonapi2.ResourceAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // TODO check why hilt can not inject moshi
    fun provideMoshi(): Moshi {
        val jsonApiAdapterFactory: JsonAdapter.Factory = ResourceAdapterFactory.builder()
            .add(SurveyData::class.java)
            .add(QuestionData::class.java)
            .add(AnswerData::class.java)
            .add(AccessTokenData::class.java)
            .build()

        return Moshi.Builder()
            .add(jsonApiAdapterFactory)
            .build()
    }

    @Provides
    fun provideNBSurveyAuthApi(
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): NBSurveyAuthApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(
            BuildConfig.BASE_URL,
            NBSurveyAuthApi::class.java, tokenAuthenticator, interceptors,
            provideMoshi()
        )
    }

    @Provides
    fun provideNBSurveyNoneAuthApi(
        noneAuthInterceptor: NoneAuthInterceptor,
    ): NBSurveyNoneAuthApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val interceptors = arrayOf(noneAuthInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(
            BuildConfig.BASE_URL,
            NBSurveyNoneAuthApi::class.java,
            null,
            interceptors,
            provideMoshi()
        )
    }
}