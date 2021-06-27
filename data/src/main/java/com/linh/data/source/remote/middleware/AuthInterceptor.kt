package com.linh.data.source.remote.middleware

import com.linh.data.source.local.AccessTokenWrapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val accessTokenWrapper: AccessTokenWrapper) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        var accessToken = ""
        var tokenType = ""
        runBlocking {
            accessTokenWrapper.getAccessToken().first()?.let {
                accessToken = it.accessToken ?: ""
                tokenType = it.tokenType ?: ""
            }
        }
        val authorisedRequestBuilder = originalRequest.newBuilder()
            .addHeader("Authorization", "$tokenType $accessToken")
            .header("Accept", "application/json")
        return chain.proceed(authorisedRequestBuilder.build())
    }
}