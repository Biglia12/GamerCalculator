package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.domain.entities.Plataforms

interface PlataformsRepository {
     suspend fun getPlataforms(): List<Platform>
     fun getPlataformsDollar(): List<Plataforms>
     fun getPlataformsPesos(): List<Plataforms>

}