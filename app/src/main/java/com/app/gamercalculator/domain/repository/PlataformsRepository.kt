package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.data.model.Platform

interface PlatformsRepository {
    suspend fun getPlatformsFromApi()
    suspend fun getAllPlatformsFromDatabase(): List<Platform>
}