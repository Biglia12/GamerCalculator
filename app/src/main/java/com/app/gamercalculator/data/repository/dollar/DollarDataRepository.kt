package com.app.gamercalculator.data.repository.dollar

import com.app.gamercalculator.data.repository.dollar.dataSource.DollarCloudDataSource
import com.app.gamercalculator.data.repository.dollar.dataSource.DollarRoomDataSource
import com.app.gamercalculator.data.repository.dollar.mappers.DollarDataMapper
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.DollarTaxes
import com.app.gamercalculator.domain.repository.DollarRepository
import javax.inject.Inject


class DollarDataRepository @Inject constructor(
    private val cloudDataSource: DollarCloudDataSource,
    private val roomDataSource: DollarRoomDataSource,
    private val dataMapper: DollarDataMapper
) : DollarRepository {

    override suspend fun getDollarFromApi() { //Obtenemos la lista del servicio y la mapeamos a VO
        val response = cloudDataSource.getDollar()
        val data = response.map { dataMapper.mapToVo(it) }
        roomDataSource.cleanTable()
        roomDataSource.insert(data)
    }

    override suspend fun getAllFromDatabase(): List<Dollar> { //tomamos la lista de room y la mapeamos a dollar
        val response = roomDataSource.getAll()
        return response.map {dataMapper.map(it)}

    }

    override suspend fun getDollarCard(): Dollar {
        val data = roomDataSource.getDollarCard()
        return dataMapper.map(data)
    }
    override suspend fun getDollarOfficial(): Dollar {
        val data = roomDataSource.getDollarOfficial()
        return dataMapper.map(data)
    }

}