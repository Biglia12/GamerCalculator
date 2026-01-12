package com.app.gamercalculator.data.repository.platforms.dataSource

import android.util.Log
import com.app.gamercalculator.data.model.PlatformDto
import com.app.gamercalculator.data.network.PlatformApiService
import javax.inject.Inject

class PlatformCloudDataSource @Inject constructor(private val apiService: PlatformApiService) {

    suspend fun getPlatforms(): List<PlatformDto> {
        val response = apiService.getPlatforms()
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