package com.comix.kreader.injection.module

import com.comix.kreader.Constants
import com.comix.kreader.model.domain.RemoteApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteApi(retrofit: Retrofit): RemoteApi = retrofit.create<RemoteApi>(RemoteApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, @Named("networkTimeoutInSeconds") timeout: Long, @Named("isDebug") isDebug: Boolean): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().connectTimeout(timeout, TimeUnit.SECONDS)
        if (isDebug) {
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    fun provideNetworkTimeoutInSeconds(): Long = Constants.NETWORK_CONNECTION_TIMEOUT
}