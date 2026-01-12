package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.repository.PlatformsRepository
import javax.inject.Inject

class GetPlatformsUseCase @Inject constructor(
    val platformsRepository: PlatformsRepository,
    val dollarRepository: DollarRepository
) {

    suspend fun getPlatformsFromApi() {
        platformsRepository.getPlatformsFromApi()
    }

    suspend fun getPlatforms(): List<Platform> {
        val platforms = platformsRepository.getAllPlatformsFromDatabase()
        val dollar = dollarRepository.getDollarOfficial()
        return platforms.map { platform ->
            if (platform.money == "Dollar") {
                platform.copy(
                    prices = platform.prices.map { priceItem ->
                        priceItem.copy(
                            amounts = priceItem.amounts.map { amount ->
                                val taxIvaDollar = calculateTax(dollar.sell, amount.price, 21)
                                val taxArcaDollar = calculateTax(dollar.sell, amount.price, 30)
                                val sumTaxesPesos = amount.price * dollar.sell + taxIvaDollar
                                amount.copy(price = sumTaxesPesos)
                            }
                        )
                    }
                )
            } else {
                platform
            }
        }
    }


    private fun calculateTax(base: Double, amount: Double, taxPercentage: Int): Double {
        return (base * amount * taxPercentage) / 100
    }



}