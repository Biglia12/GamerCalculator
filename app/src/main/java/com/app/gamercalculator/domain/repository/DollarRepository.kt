package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.DollarTaxes

interface DollarRepository {
    suspend fun getDollarFromApi()
    suspend fun getAllFromDatabase(): List<Dollar>
    suspend fun getDollarCard(): Dollar
    suspend fun getDollarMep(): Dollar
    suspend fun getDollarCripto(): Dollar
    suspend fun getDollarOfficial(): Dollar
}