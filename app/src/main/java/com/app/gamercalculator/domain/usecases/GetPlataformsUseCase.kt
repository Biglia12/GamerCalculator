package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.R
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.repository.PlataformsRepository
import javax.inject.Inject

class GetPlataformsUseCase @Inject constructor(val plataformsRepository: PlataformsRepository) {
    fun getPlataformsDollar(): List<Plataforms> {
        return plataformsRepository.getPlataformsDollar()
    }

    fun getPlataformsPesos(): List<Plataforms> {
        return plataformsRepository.getPlataformsPesos()
    }


}