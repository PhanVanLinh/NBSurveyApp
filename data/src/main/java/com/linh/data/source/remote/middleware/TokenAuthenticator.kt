package com.linh.data.source.remote.middleware

import com.linh.data.source.local.AccessTokenWrapper
import com.linh.domain.base.Result
import com.linh.domain.repository.datasource.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val accessTokenWrapper: AccessTokenWrapper,
    private val authRemoteDataSource: AuthRemoteDataSource
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        var newAccessToken = ""
        var newTokenType = ""

        runBlocking {
            val refreshToken = accessTokenWrapper.getAccessToken().first()?.refreshToken ?: ""
            val result = authRemoteDataSource.refreshToken(refreshToken)
            if (result is Result.Success) {
                newAccessToken = result.data.accessToken ?: ""
                newTokenType = result.data.tokenType ?: ""
            }
        }
        return response.request().newBuilder()
            .header("Authorization", "$newTokenType $newAccessToken")
            .build()
    }
}