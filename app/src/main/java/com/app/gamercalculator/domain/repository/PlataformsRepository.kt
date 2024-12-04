package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.domain.entities.Plataforms

interface PlataformsRepository {
     fun getPlataformsDollar(): List<Plataforms>
     fun getPlataformsPesos(): List<Plataforms>

}