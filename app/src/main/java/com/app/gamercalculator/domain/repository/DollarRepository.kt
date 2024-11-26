package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.domain.entities.Dollar

interface DollarRepository {
    suspend fun getDollarFromApi()
    suspend fun getAllFromDatabase(): List<Dollar>
}