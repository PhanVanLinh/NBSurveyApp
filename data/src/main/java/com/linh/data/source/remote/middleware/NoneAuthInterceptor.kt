package com.linh.data.source.remote.middleware

import okhttp3.Interceptor
import okhttp3.Response

class NoneAuthInterceptor constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}