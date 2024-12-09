package com.app.gamercalculator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.gamercalculator.data.database.entities.DollarVO
import retrofit2.http.GET

@Dao
interface DollarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dollar: List<DollarVO>)

    @Query("SELECT * FROM Dollar_table")
    suspend fun getAll(): List<DollarVO>

    @Query("SELECT * FROM Dollar_table WHERE house = 'bolsa'")
    suspend fun getDollarMep(): DollarVO

    @Query("SELECT * FROM Dollar_table WHERE house = 'tarjeta'")
    suspend fun getDollarCard(): DollarVO

    @Query("SELECT * FROM Dollar_table WHERE house = 'cripto'")
    suspend fun getDollarCripto(): DollarVO

    @Query("SELECT sell FROM Dollar_table WHERE house = 'bolsa'")
    suspend fun getSellDollarMep(): Double

    @Query("SELECT sell FROM Dollar_table WHERE house = 'tarjeta'")
    suspend fun getSellDollarCard(): Double

    @Query("SELECT sell FROM Dollar_table WHERE house = 'cripto'")
    suspend fun getSellDollarCripto(): Double



    @Query("DELETE FROM Dollar_table")
    suspend fun delete()


}