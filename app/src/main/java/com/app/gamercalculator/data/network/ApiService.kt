package com.app.gamercalculator.data.network

import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.model.PlatformDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v1/dolares")
    suspend fun getDollar(): Response<List<DollarResponse>>

}


