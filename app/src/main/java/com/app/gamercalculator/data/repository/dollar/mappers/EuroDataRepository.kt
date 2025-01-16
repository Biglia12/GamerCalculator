package com.app.gamercalculator.data.repository.dollar.mappers

import android.util.Log
import com.app.gamercalculator.data.repository.dollar.dataSource.DollarCloudDataSource
import com.app.gamercalculator.data.repository.dollar.dataSource.EuroCloudDataSource
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.repository.EuroRepository
import javax.inject.Inject


class EuroDataRepository @Inject constructor(
    private val cloudDataSource: EuroCloudDataSource) : EuroRepository {

    override suspend fun getEuroApi() {
        val response = cloudDataSource.getEuro()
        Log.i("EuroRepository", response.toString())
   }
}


