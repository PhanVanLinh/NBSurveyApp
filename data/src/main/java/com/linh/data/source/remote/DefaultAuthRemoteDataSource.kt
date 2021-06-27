package com.linh.data.source.remote

import com.linh.data.BuildConfig
import com.linh.data.di.qualifier.IODispatcher
import com.linh.data.source.mapper.AccessTokenDataMapper
import com.linh.data.source.remote.api.NBSurveyNoneAuthApi
import com.linh.data.source.remote.model.GrantType
import com.linh.data.source.remote.request.LoginRequest
import com.linh.data.source.remote.request.RefreshTokenRequest
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
            grantType = GrantType.PASSWORD.value,
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

    override suspend fun refreshToken(refreshToken:String): Result<AccessToken> {
        val refreshTokenRequest = RefreshTokenRequest(
            grantType = GrantType.REFRESH_TOKEN.value,
            refreshToken = refreshToken,
            clientId = BuildConfig.OAUTH_CLIENT_ID,
            clientSecret = BuildConfig.OAUTH_CLIENT_SECRET,
        )
        val refreshTokenRequestJson =
            Moshi.Builder().build().adapter(RefreshTokenRequest::class.java).toJson(refreshTokenRequest)
        return withContext(dispatcher) {
            getResult {
                accessTokenDataMapper.accessTokenDataToAccessToken(
                    nbSurveyNoneAuthApi.refreshToken(
                        refreshTokenRequestJson
                    )
                )
            }
        }
    }
}