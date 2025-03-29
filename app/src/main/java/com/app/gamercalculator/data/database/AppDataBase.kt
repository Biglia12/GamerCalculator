package com.app.gamercalculator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.gamercalculator.data.database.dao.DollarDao
import com.app.gamercalculator.data.database.entities.DollarVO

@Database(entities = [DollarVO::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun dollarDao(): DollarDao

}