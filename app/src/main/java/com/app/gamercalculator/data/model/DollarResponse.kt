package com.app.gamercalculator.data.model

import com.google.gson.annotations.SerializedName

data class DollarResponse(
    @SerializedName("casa")
    val house: String,
    @SerializedName("compra")
    val buy: Double,
    @SerializedName("fechaActualizacion")
    val date: String,
    @SerializedName("moneda")
    val money: String,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("venta")
    val sell: Double
)

