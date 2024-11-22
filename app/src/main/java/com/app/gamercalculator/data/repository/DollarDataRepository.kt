package com.app.gamercalculator.data.repository

import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.network.ApiService
import com.app.gamercalculator.domain.repository.DollarRepository
import javax.inject.Inject


class DollarDataRepository @Inject constructor(private val apiService: ApiService): DollarRepository {

    override suspend fun getDollar(): List<DollarResponse> {
        val response = apiService.getDollar()
        return response.body() ?: emptyList()
    }

}