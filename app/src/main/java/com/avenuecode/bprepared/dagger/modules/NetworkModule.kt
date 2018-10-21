package com.avenuecode.bprepared.dagger.modules

import com.avenuecode.bprepared.BuildConfig
import com.avenuecode.bprepared.network.NetworkClient
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val TIMEOUT_VALUE = 180
    private val TIMEOUT_UNIT = TimeUnit.SECONDS

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://35.185.7.39/")
                .client(getHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideNetworkClient(retrofit: Retrofit): NetworkClient {
        return retrofit.create(NetworkClient::class.java)
    }


    private fun getHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient: OkHttpClient

        okHttpClient = OkHttpClient.Builder().connectTimeout(TIMEOUT_VALUE.toLong(), TIMEOUT_UNIT)
                .writeTimeout(TIMEOUT_VALUE.toLong(), TIMEOUT_UNIT)
                .readTimeout(TIMEOUT_VALUE.toLong(), TIMEOUT_UNIT).addInterceptor(interceptor).build()
        return okHttpClient
    }
}