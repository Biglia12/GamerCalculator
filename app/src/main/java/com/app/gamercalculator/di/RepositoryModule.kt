package com.app.gamercalculator.di

import com.app.gamercalculator.data.repository.dollar.DollarDataRepository
import com.app.gamercalculator.data.repository.dollar.dataSource.DollarCloudDataSource
import com.app.gamercalculator.data.repository.dollar.dataSource.DollarRoomDataSource
import com.app.gamercalculator.data.repository.dollar.mappers.DollarDataMapper
import com.app.gamercalculator.data.repository.plataforms.PlatformsDataRepository
import com.app.gamercalculator.data.repository.plataforms.dataSource.PlatformCloudDataSource
import com.app.gamercalculator.data.repository.settings.SettingsDataRepository
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.repository.PlataformsRepository
import com.app.gamercalculator.utils.JsonFileReader
import com.app.gamercalculator.domain.repository.SettingsRepository
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
    fun providePlataformsRepository(
        cloudDataSource: PlatformCloudDataSource,
        jsonFileReader: JsonFileReader
    ): PlataformsRepository {
        return PlatformsDataRepository(cloudDataSource,jsonFileReader)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
    ): SettingsRepository {
        return SettingsDataRepository()
    }

}