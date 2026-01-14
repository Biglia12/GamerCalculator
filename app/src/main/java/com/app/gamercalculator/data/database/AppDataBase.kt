package com.app.gamercalculator.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import com.app.gamercalculator.data.database.converters.PlatformConverters
import com.app.gamercalculator.data.database.dao.DollarDao
import com.app.gamercalculator.data.database.dao.PlatformDao
import com.app.gamercalculator.data.database.entities.DollarVO
import com.app.gamercalculator.data.database.entities.PlatformVO

@Database(entities = [DollarVO::class, PlatformVO::class], version = 2)

@TypeConverters(PlatformConverters::class)

abstract class AppDataBase : RoomDatabase() {

    abstract fun dollarDao(): DollarDao
    abstract fun platformDao(): PlatformDao


}