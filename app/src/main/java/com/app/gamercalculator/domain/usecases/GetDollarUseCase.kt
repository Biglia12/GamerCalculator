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

    suspend fun getDollarCard(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarCard = repository.getDollarCard()
        val dollarOfficial = repository.getDollarOfficial()

        val dollarCardWitTaxes = DollarTaxes(
            name = dollarCard.name,
            date = dollarCard.date,
            taxIva = ruleThreeAndInputNumber(dollarOfficial.buy, inputNumber.toDouble(), 21),
            taxArca = ruleThreeAndInputNumber(dollarOfficial.buy, inputNumber.toDouble(), 30),
            mountTotal = if (isDollarChecked) {
                dollarCard.sell.toInt() * inputNumber.toDouble()
            } else {
                dollarCard.sell.toInt() + inputNumber.toDouble()
            }
        )
        return dollarCardWitTaxes
    }

    suspend fun getDollarMep(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarMep = repository.getDollarMep()
        val countDollarMep: Double = inputNumber.toDouble() * dollarMep.sell
        val countDollarPesos: Double = inputNumber.toDouble() / dollarMep.sell
        val dollarMepWitTaxes = DollarTaxes(
            name = dollarMep.name,
            date = dollarMep.date,
            taxIva = 0.0,
            taxArca = 0.0,
            mountTotal = if (isDollarChecked) {
                countDollarMep
            } else {
                countDollarPesos
            }
        )
        return dollarMepWitTaxes
    }

    suspend fun getDollarCripto (inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarCripto = repository.getDollarCripto()
        val countDollarCripto: Double = inputNumber.toDouble() * dollarCripto.sell
        val countPesosCripto: Double = inputNumber.toDouble() / dollarCripto.sell
        val dollarCriptoWitTaxes = DollarTaxes(
            name = dollarCripto.name,
            date = dollarCripto.date,
            taxIva = 0.0,
            taxArca = 0.0,
            mountTotal = if (isDollarChecked) {
                countDollarCripto
            } else {
                countPesosCripto
            }
        )
        return dollarCriptoWitTaxes
    }


    suspend fun getDollarCardDigital(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarOfficial = repository.getDollarOfficial()
        val taxIvaDollar = ruleThreeAndInputNumber(dollarOfficial.sell, inputNumber.toDouble(), 21)
        val taxArcaDollar = ruleThreeAndInputNumber(dollarOfficial.sell, inputNumber.toDouble(), 30)
        val sumTaxesDollar = taxIvaDollar + taxArcaDollar + dollarOfficial.sell * inputNumber.toDouble()

        val taxIvaPesos = divideInputNumberTax(inputNumber.toDouble(), 21)
        val taxArcaPesos = divideInputNumberTax(inputNumber.toDouble(), 30)
        val sumTaxesPesos = taxIvaPesos + taxArcaPesos + inputNumber.toDouble()

        val dollarCardWitTaxes = DollarTaxes(
            name = dollarOfficial.name,
            date = dollarOfficial.date,
            taxIva = if (isDollarChecked) taxIvaDollar else taxIvaPesos,
            taxArca = if (isDollarChecked) taxArcaDollar else taxArcaPesos,
            mountTotal = if (isDollarChecked) {
                sumTaxesDollar
            } else {
                sumTaxesPesos
            }
        )
        return dollarCardWitTaxes
    }

    private fun ruleThreeAndInputNumber(dollar: Double, inputNumber: Double, tax: Int): Double {
        val dollarInputNumber = dollar * inputNumber
        return (dollarInputNumber * tax) / 100
    }

    private fun divideInputNumberTax( inputNumber: Double, taxArca: Int): Double {
        return (inputNumber * taxArca) / 100
    }

}