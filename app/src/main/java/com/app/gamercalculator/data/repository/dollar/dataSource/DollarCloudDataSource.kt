package com.app.gamercalculator.data.repository.dollar.dataSource

import android.util.Log
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.network.ApiService
import javax.inject.Inject

class DollarCloudDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getDollar(): List<DollarResponse> {
        val response = apiService.getDollar()
        try {
            if (response.isSuccessful){
                return response.body()!!
            }
        }catch (e: Exception){
            Log.e("ErrorService", e.message.toString())
        }
        return emptyList()
    }
}