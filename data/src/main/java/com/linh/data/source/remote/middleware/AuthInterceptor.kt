package com.linh.data.source.remote.middleware

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor constructor(private val accessToken: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorisedRequestBuilder = originalRequest.newBuilder()
                .addHeader("Authorization", accessToken)
                .header("Accept", "application/json")
        return chain.proceed(authorisedRequestBuilder.build())
    }
}