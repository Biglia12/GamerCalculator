package com.app.gamercalculator.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PricePlanVO(
    @ColumnInfo(name = "type")
    val type: String = "",

    @ColumnInfo(name = "amounts")
    val amounts: List<AmountVO> = emptyList()
)



