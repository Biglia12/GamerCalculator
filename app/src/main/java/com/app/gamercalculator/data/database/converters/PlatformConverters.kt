package com.app.gamercalculator.data.database.converters

import androidx.room.TypeConverter
import com.app.gamercalculator.data.database.entities.PricePlanVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlatformConverters {

    private val gson = Gson()

    // PricePlanVO list
    @TypeConverter
    fun fromPricePlanList(value: List<PricePlanVO>): String =
        gson.toJson(value)

    @TypeConverter
    fun toPricePlanList(value: String): List<PricePlanVO> =
        gson.fromJson(
            value,
            object : TypeToken<List<PricePlanVO>>() {}.type
        )
}

