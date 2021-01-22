package com.androidsystems.bayadcenterapp.data.network.base

import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptorImpl @Inject constructor(): RequestInterceptor {

    override fun intercept(chain: Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}