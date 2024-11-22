package com.app.gamercalculator.data.repository

import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.network.ApiService
import javax.inject.Inject


class DollarDataRepository @Inject constructor (private val apiService: ApiService) {

    suspend fun getDollars(): List<DollarResponse> {
        val response = apiService.getDollar()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("API request failed with code ${response.code()}")
        }
    }

}