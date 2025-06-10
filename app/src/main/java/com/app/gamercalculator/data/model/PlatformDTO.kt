package com.app.gamercalculator.data.model

import com.google.gson.annotations.SerializedName

data class PlatformDto(
    @SerializedName("imageName") val imageName: String,
    @SerializedName("name") val name: String,
    @SerializedName("money") val money: String,
    @SerializedName("prices") val prices: List<PricePlanDto>
)

data class PricePlanDto(
    @SerializedName("type") val type: String,
    @SerializedName("amounts") val amounts: List<AmountDto>
)

data class AmountDto(
    @SerializedName("period") val period: String,
    @SerializedName("price") val price: Double
)