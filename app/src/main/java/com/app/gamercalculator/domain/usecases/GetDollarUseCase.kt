package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.repository.DollarRepository
import javax.inject.Inject

class GetDollarUseCase @Inject constructor(
    private val repository: DollarRepository
) {

    suspend fun getDollarFromApi() {
        repository.getDollarFromApi()
    }

    suspend fun getAllFromDatabase(): List<Dollar> {
        return repository.getAllFromDatabase()

    }

}