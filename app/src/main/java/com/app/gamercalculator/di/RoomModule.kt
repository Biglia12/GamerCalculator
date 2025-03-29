package com.app.gamercalculator.di

import android.content.Context
import androidx.room.Room
import com.app.gamercalculator.data.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "dollar_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDollarDao(db: AppDataBase) = db.dollarDao()
}