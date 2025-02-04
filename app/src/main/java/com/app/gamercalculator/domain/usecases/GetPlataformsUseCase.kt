package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.repository.PlataformsRepository
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class GetPlataformsUseCase @Inject constructor(
    val plataformsRepository: PlataformsRepository,
    val dollarRepository: DollarRepository
) {

    suspend fun getPlataforms(): List<Platform> {
        val platforms = plataformsRepository.getPlataforms()
        val dollar = dollarRepository.getDollarOfficial()
        return platforms.map { platform ->
            if (platform.money == "Dollar") {
                platform.copy(
                    prices = platform.prices.map { priceItem ->
                        priceItem.copy(
                            amounts = priceItem.amounts.map { amount ->
                                val taxIvaDollar = calculateTax(dollar.sell, amount.price, 21)
                                val taxArcaDollar = calculateTax(dollar.sell, amount.price, 30)
                                val sumTaxesPesos = amount.price * dollar.sell + taxArcaDollar + taxIvaDollar
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

    fun getPlataformsDollar(): List<Plataforms> {
        return plataformsRepository.getPlataformsDollar()
    }

    fun getPlataformsPesos(): List<Plataforms> {
        return plataformsRepository.getPlataformsPesos()
    }

    private fun calculateTax(base: Double, amount: Double, taxPercentage: Int): Double {
        return (base * amount * taxPercentage) / 100
    }



}