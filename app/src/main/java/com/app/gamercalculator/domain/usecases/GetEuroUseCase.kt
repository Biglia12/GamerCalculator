package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.domain.repository.EuroRepository
import javax.inject.Inject

class GetEuroUseCase @Inject constructor(
    private val repository: EuroRepository
) {
    suspend fun getEuroApi() {
        repository.getEuroApi()
    }
}