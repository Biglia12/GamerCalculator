package com.app.gamercalculator.data.database.entities

import androidx.room.ColumnInfo

data class AmountVO(
    @ColumnInfo(name = "period")
    val period: String = "",

    @ColumnInfo(name = "price")
    val price: Double = 0.0
)


