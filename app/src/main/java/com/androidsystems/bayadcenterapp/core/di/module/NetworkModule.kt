package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.BuildConfig
import com.androidsystems.bayadcenterapp.data.network.base.ConnectivityInterceptor
import com.androidsystems.bayadcenterapp.data.network.base.ConnectivityInterceptorImpl
import com.androidsystems.bayadcenterapp.data.network.base.NetworkServiceApi
import com.androidsystems.bayadcenterapp.data.network.base.RequestInterceptor
import com.androidsystems.bayadcenterapp.data.network.base.RequestInterceptorImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun providesRequestInterceptor(requestInterceptorImpl: RequestInterceptorImpl): RequestInterceptor {
        return requestInterceptorImpl
    }

    @Provides
    fun providesConnectivityInterceptor(connectivityInterceptorImpl: ConnectivityInterceptorImpl): ConnectivityInterceptor {
        return connectivityInterceptorImpl
    }

    @Provides
    fun providesOkHttpClient(
        requestInterceptor: RequestInterceptor,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(logging)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gsonBuilder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = gsonBuilder.create()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_DOMAIN)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun providesNetworkApiService(retrofit: Retrofit): NetworkServiceApi {
        return retrofit.create(NetworkServiceApi::class.java)
    }
}