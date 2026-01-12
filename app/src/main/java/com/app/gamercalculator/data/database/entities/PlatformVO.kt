package com.app.gamercalculator.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platform")
data class PlatformVO(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "image_name")
    val imageName: String = "",

    @ColumnInfo(name = "money")
    val money: String = "",

    // Se guarda como JSON
    @ColumnInfo(name = "prices")
    val prices: List<PricePlanVO> = emptyList()
)


