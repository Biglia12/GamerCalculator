package com.app.gamercalculator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientModule {
    const val READ_TIMEOUT = 3L
    const val CONNECT_TIMEOUT = 3L

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .build()
}