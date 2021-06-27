package com.linh.data.source.remote.service

import com.squareup.moshi.Moshi
import moe.banana.jsonapi2.JsonApiConverterFactory
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object {

        private const val CONNECTION_TIMEOUT = 15L

        fun <T> generate(
            baseUrl: String, serviceClass: Class<T>,
            authenticator: Authenticator?, interceptors: Array<Interceptor>,
            moshi: Moshi
        ): T {
            val okHttpClientBuilder = OkHttpClient().newBuilder()

            for (interceptor in interceptors) {
                okHttpClientBuilder.addInterceptor(interceptor)
            }
            okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            if (authenticator != null) {
                okHttpClientBuilder.authenticator(authenticator)
            }
            val okHttpClient = okHttpClientBuilder.build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JsonApiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
            return retrofit.create(serviceClass)
        }
    }
}