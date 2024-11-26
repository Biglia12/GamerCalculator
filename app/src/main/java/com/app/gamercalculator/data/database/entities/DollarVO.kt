package com.app.gamercalculator.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Dollar_table"
)
data class DollarVO(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "house")
    val house: String,
    @ColumnInfo(name = "buy")
    val buy: Double,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "money")
    val money: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sell")
    val sell: Double
)