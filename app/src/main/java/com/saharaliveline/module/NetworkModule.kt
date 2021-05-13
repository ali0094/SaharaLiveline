package com.saharaliveline.module

import com.saharaliveline.BuildConfig
import com.saharaliveline.data.api.NetworkService
import com.saharaliveline.data.preferences.AppPreferences
import com.saharaliveline.util.AuthTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideHtttpClient(appPreferences: AppPreferences): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(AuthTokenInterceptor(appPreferences))
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): NetworkService =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(NetworkService::class.java)
}