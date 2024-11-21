package com.app.gamercalculator.data.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v1/dolares")
    suspend fun getDollar(): Response<Any>

}


