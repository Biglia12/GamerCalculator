package com.app.gamercalculator.di

import com.app.gamercalculator.data.network.ApiService
import com.app.gamercalculator.data.network.Constants
import com.app.gamercalculator.data.network.PlatformApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkPlatformModule {

    @Singleton
    @Provides
    @Named("PlatformRetrofit")
    fun providePlatformRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_PLATFORM)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providePlatformApiService(@Named("PlatformRetrofit") retrofit: Retrofit): PlatformApiService =
        retrofit.create(PlatformApiService::class.java)
}