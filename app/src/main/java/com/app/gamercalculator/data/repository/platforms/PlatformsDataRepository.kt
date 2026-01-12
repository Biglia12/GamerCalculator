package com.app.gamercalculator.data.repository.platforms

import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.data.repository.platforms.dataSource.PlatformCloudDataSource
import com.app.gamercalculator.data.repository.platforms.dataSource.PlatformRoomDataSource
import com.app.gamercalculator.data.repository.platforms.mapper.PlatformDataMapper
import com.app.gamercalculator.domain.repository.PlatformsRepository
import com.app.gamercalculator.utils.JsonFileReader
import javax.inject.Inject

class PlatformsDataRepository @Inject constructor(
    val cloudDataSource: PlatformCloudDataSource,
    private val roomDataSource: PlatformRoomDataSource,
    private val dataMapper: PlatformDataMapper
) : PlatformsRepository {


    override suspend fun getPlatformsFromApi(){
        val response = cloudDataSource.getPlatforms()
        val data = response.map { dataMapper.mapToVo(it) }
        roomDataSource.cleanTable()
        roomDataSource.insert(data)
        //return response.toList()
        // return jsonFileReader.readJsonFromAssets("platforms.json")
    }

    override suspend fun getAllPlatformsFromDatabase() : List<Platform> {
            val response = roomDataSource.getAll()
            return response.map {dataMapper.mapToDomain(it)}
    }



}