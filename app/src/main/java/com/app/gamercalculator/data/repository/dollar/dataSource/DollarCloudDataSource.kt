package com.app.gamercalculator.data.repository.dollar.dataSource

import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.network.ApiService
import javax.inject.Inject

class DollarCloudDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getDollar(): List<DollarResponse> {
        val response = apiService.getDollar()
        return response.body() ?: emptyList()
    }
}