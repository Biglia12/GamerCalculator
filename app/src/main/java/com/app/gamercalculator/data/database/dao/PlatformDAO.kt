package com.app.gamercalculator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.gamercalculator.data.database.entities.PlatformVO


@Dao
interface PlatformDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(platforms: List<PlatformVO>)

    @Query("SELECT * FROM platform")
    suspend fun getAll(): List<PlatformVO>

    @Query("DELETE FROM platform")
    suspend fun deleteAll()
}
