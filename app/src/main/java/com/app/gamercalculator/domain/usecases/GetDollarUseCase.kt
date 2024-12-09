package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.DollarTaxes
import com.app.gamercalculator.domain.repository.DollarRepository
import javax.inject.Inject

class GetDollarUseCase @Inject constructor(
    private val repository: DollarRepository
) {

    suspend fun getDollarFromApi() {
        repository.getDollarFromApi()
    }

    suspend fun getAllFromDatabase(): List<Dollar> {
        return repository.getAllFromDatabase()

    }

    suspend fun getDollarCard(inputNumber: String): DollarTaxes {
        val dollarCard = repository.getDollarCard()
        val dollarOfficial = repository.getDollarOfficial()

        val dollarCardWitTaxes = DollarTaxes(
            name = dollarCard.name,
            date = dollarCard.date,
            taxIva =  ruleThreeAndInputNumber(dollarOfficial.buy.toInt(), inputNumber.toInt(), 21),
            taxCountry = ruleThreeAndInputNumber(dollarOfficial.buy.toInt(),inputNumber.toInt(), 8),
            taxArca = ruleThreeAndInputNumber(dollarOfficial.buy.toInt(),inputNumber.toInt(), 30),
            mountTotal = dollarCard.sell.toInt() * inputNumber.toInt()
        )
        return dollarCardWitTaxes
    }

    private fun ruleThreeAndInputNumber (dollar: Int, inputNumber: Int, tax: Int): Int{
        val dollarInputNumber = dollar * inputNumber
        return (dollarInputNumber * tax) / 100
    }

}