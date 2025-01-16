package com.app.gamercalculator.data.repository.dollar.dataSource

import android.util.Log
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.model.EuroResponse
import com.app.gamercalculator.data.network.ApiService
import javax.inject.Inject

class EuroCloudDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getEuro(): EuroResponse? {
        val response = apiService.getEuro()
        try {
            if (response.isSuccessful){
                return response.body()!!
            }
        }catch (e: Exception){
            Log.e("ErrorService", e.message.toString())
        }
        return null
    }
}

