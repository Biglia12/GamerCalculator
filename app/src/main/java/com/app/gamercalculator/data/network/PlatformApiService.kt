package com.app.gamercalculator.data.network

import com.app.gamercalculator.data.model.PlatformDto
import retrofit2.Response
import retrofit2.http.GET

interface PlatformApiService {
    @GET("platforms.json")
    suspend fun getPlatforms(): Response<List<PlatformDto>>
}