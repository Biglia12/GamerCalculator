package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.data.model.DollarResponse

interface DollarRepository {
    suspend fun getDollar(): List<DollarResponse>
}