package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.repository.PlataformsRepository
import javax.inject.Inject

class GetPlataformsUseCase @Inject constructor(val plataformsRepository: PlataformsRepository) {

    fun getPlataforms(): List<Platform> {
        return plataformsRepository.getPlataforms()
    }

    fun getPlataformsDollar(): List<Plataforms> {
        return plataformsRepository.getPlataformsDollar()
    }

    fun getPlataformsPesos(): List<Plataforms> {
        return plataformsRepository.getPlataformsPesos()
    }


}