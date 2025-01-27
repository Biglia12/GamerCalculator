package com.app.gamercalculator.di

import android.content.Context
import com.app.gamercalculator.utils.JsonFileReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonModule {

    @Provides
    @Singleton
    fun provideJsonFileReader(@ApplicationContext context: Context): JsonFileReader {
        return JsonFileReader(context)
    }

}