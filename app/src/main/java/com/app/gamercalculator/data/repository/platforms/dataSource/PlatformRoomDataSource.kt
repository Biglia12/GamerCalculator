package com.app.gamercalculator.data.repository.platforms.dataSource

import com.app.gamercalculator.data.database.AppDataBase
import com.app.gamercalculator.data.database.entities.PlatformVO
import javax.inject.Inject

class PlatformRoomDataSource @Inject constructor(private val db: AppDataBase) {

    suspend fun insert(platforms: List<PlatformVO>) {
        db.platformDao().insert(platforms)
    }

    suspend fun getAll(): List<PlatformVO> {
        return db.platformDao().getAll()
    }

    suspend fun cleanTable() {
        db.platformDao().deleteAll()
    }

}