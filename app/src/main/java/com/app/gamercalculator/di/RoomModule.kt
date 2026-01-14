package com.app.gamercalculator.di

import android.content.Context
import androidx.room.Room
import com.app.gamercalculator.data.database.AppDataBase
import com.app.gamercalculator.data.database.MIGRATION_1_2
import com.app.gamercalculator.data.database.dao.PlatformDao
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
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).addMigrations(MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideDollarDao(db: AppDataBase) = db.dollarDao()

    @Singleton
    @Provides
    fun providePlatformDao(db: AppDataBase): PlatformDao =
        db.platformDao()

}