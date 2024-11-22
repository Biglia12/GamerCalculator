package com.app.gamercalculator.data

import com.app.gamercalculator.data.network.ApiService
import com.app.gamercalculator.data.repository.DollarDataRepository
import com.app.gamercalculator.domain.repository.DollarRepository
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
    fun provideDollarRepository(apiService: ApiService): DollarRepository {
        return DollarDataRepository(apiService)
    }
}