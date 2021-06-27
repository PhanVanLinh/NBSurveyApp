package com.linh.data.source.remote

import com.linh.data.BuildConfig
import com.linh.data.di.qualifier.IODispatcher
import com.linh.data.source.mapper.AccessTokenDataMapper
import com.linh.data.source.remote.api.NBSurveyNoneAuthApi
import com.linh.data.source.remote.request.LoginRequest
import com.linh.domain.base.Result
import com.linh.domain.model.AccessToken
import com.linh.domain.repository.datasource.remote.AuthRemoteDataSource
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultAuthRemoteDataSource @Inject constructor(
    private val nbSurveyNoneAuthApi: NBSurveyNoneAuthApi,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher,
    private val accessTokenDataMapper: AccessTokenDataMapper
) : BaseRemoteDataSource(), AuthRemoteDataSource {

    override suspend fun login(email: String, password: String): Result<AccessToken> {
        val loginRequest = LoginRequest(
            grantType = BuildConfig.GRANT_TYPE,
            email = email,
            password = password,
            clientId = BuildConfig.OAUTH_CLIENT_ID,
            clientSecret = BuildConfig.OAUTH_CLIENT_SECRET,
        )
        val loginRequestJson =
            Moshi.Builder().build().adapter(LoginRequest::class.java).toJson(loginRequest)
        return withContext(dispatcher) {
            getResult {
                accessTokenDataMapper.accessTokenDataToAccessToken(
                    nbSurveyNoneAuthApi.login(
                        loginRequestJson
                    )
                )
            }
        }
    }
}