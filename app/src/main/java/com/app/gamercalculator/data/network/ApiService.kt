package com.app.gamercalculator.data.network

import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.model.EuroResponse
import com.google.common.base.Objects
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v1/dolares")
    suspend fun getDollar(): Response<List<DollarResponse>>

    @GET("/v1/cotizaciones/eur")
    suspend fun getEuro(): Response<EuroResponse>

}


