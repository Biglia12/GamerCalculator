package com.app.gamercalculator.data.repository

import com.app.gamercalculator.data.network.ApiService
import javax.inject.Inject


class DollarDataRepository @Inject constructor (private val apiService: ApiService) {

    suspend fun getDollars(): Any {
        val response = apiService.getDollar()
        if (response.isSuccessful) {
            return response
        } else {
            throw Exception("API request failed with code ${response.code()}")
        }
    }

}