package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.domain.repository.DollarRepository
import javax.inject.Inject

class GetDollarUseCase @Inject constructor (private val dollarRepository: DollarRepository) {

    suspend fun getDollar(): List<DollarResponse> {
        return dollarRepository.getDollar()
    }

}