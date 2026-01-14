package com.app.gamercalculator.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS platform (
                name TEXT NOT NULL PRIMARY KEY,
                image_name TEXT NOT NULL,
                money TEXT NOT NULL,
                prices TEXT NOT NULL
            )
            """.trimIndent()
        )
    }
}