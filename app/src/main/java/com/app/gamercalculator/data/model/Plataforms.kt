package com.app.gamercalculator.data.model


data class Platform(
    val imageName: String, // Nombre del drawable, p. ej., "ic_ea"
    val name: String, // Nombre de la plataforma, p. ej., "EA Play"
    val prices: List<PricePlan> // Lista de planes de precios
)

data class PricePlan(
    val type: String, // Tipo de plan, p. ej., "Standard", "Deluxe", etc.
    val amounts: List<Amount> // Lista de precios por periodo
)

data class Amount(
    val period: String, // Periodo, p. ej., "Monthly", "Quarterly", etc.
    val price: Int // Precio del plan en la moneda correspondiente
)

 data class Platforms (val list : List<Platform>)
