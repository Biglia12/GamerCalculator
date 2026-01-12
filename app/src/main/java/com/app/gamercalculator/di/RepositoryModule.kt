package com.app.gamercalculator.di

import com.app.gamercalculator.data.repository.dollar.DollarDataRepository
import com.app.gamercalculator.data.repository.dollar.dataSource.DollarCloudDataSource
import com.app.gamercalculator.data.repository.dollar.dataSource.DollarRoomDataSource
import com.app.gamercalculator.data.repository.dollar.mappers.DollarDataMapper
import com.app.gamercalculator.data.repository.platforms.PlatformsDataRepository
import com.app.gamercalculator.data.repository.platforms.dataSource.PlatformCloudDataSource
import com.app.gamercalculator.data.repository.platforms.dataSource.PlatformRoomDataSource
import com.app.gamercalculator.data.repository.platforms.mapper.PlatformDataMapper
import com.app.gamercalculator.data.repository.settings.SettingsDataRepository
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.repository.PlatformsRepository
import com.app.gamercalculator.domain.repository.SettingsRepository
import com.app.gamercalculator.utils.JsonFileReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDollarRepository(
        cloudDataSource: DollarCloudDataSource,
        roomDataSource: DollarRoomDataSource,
        dataMapper: DollarDataMapper
    ): DollarRepository {
        return DollarDataRepository(cloudDataSource, roomDataSource, dataMapper)
    }


    @Provides
    @Singleton
    fun providePlatformsRepository(
        cloudDataSource: PlatformCloudDataSource,
        roomDataSource: PlatformRoomDataSource,
        dataMapper: PlatformDataMapper
    ): PlatformsRepository {
        return PlatformsDataRepository(
            cloudDataSource,
            roomDataSource,
            dataMapper
        )
    }


    @Provides
    @Singleton
    fun provideSettingsRepository(
    ): SettingsRepository {
        return SettingsDataRepository()
    }

}