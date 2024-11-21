package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.data.repository.DollarDataRepository
import com.app.gamercalculator.domain.repository.DollarRepository
import javax.inject.Inject

class GetDollarUseCase/* @Inject constructor*/(private val dollarRepository: DollarDataRepository): DollarRepository {

    override suspend fun getDollar(): Any {
        return dollarRepository.getDollars()
    }

}