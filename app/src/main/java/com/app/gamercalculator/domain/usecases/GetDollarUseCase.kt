package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.DollarTaxes
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.utils.DateUtils
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
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
                formatMount(dollarCard.sell.toInt() * inputNumber.toDouble())
            } else {
                formatMount(dollarCard.sell.toInt() + inputNumber.toDouble())
            }
        )
        return dollarCardWitTaxes
    }

    suspend fun getDollarMep(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarMep = repository.getDollarMep()
        val countDollarMep = formatMount(inputNumber.toDouble() * dollarMep.sell)
        val countDollarPesos = formatMount(inputNumber.toDouble() / dollarMep.sell)
        val dollarMepWitTaxes = DollarTaxes(
            name = dollarMep.name,
            date = formateDate(dollarMep.date),
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
        val countDollarCripto = formatMount(inputNumber.toDouble() * dollarCripto.sell)
        val countPesosCripto = formatMount(inputNumber.toDouble() / dollarCripto.sell)
        val dollarCriptoWitTaxes = DollarTaxes(
            name = dollarCripto.name,
            date = formateDate(dollarCripto.date),
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
        val sumTaxesDollar = formatMount(taxIvaDollar + taxArcaDollar + dollarOfficial.sell * inputNumber.toDouble())

        val taxIvaPesos = divideInputNumberTax(inputNumber.toDouble(), 21)
        val taxArcaPesos = divideInputNumberTax(inputNumber.toDouble(), 30)
        val sumTaxesPesos = formatMount(taxIvaPesos + taxArcaPesos + inputNumber.toDouble())

        val dollarCardWitTaxes = DollarTaxes(
            name = dollarOfficial.name,
            date = formateDate(dollarOfficial.date),
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

    private fun formateDate(date: String): String {
        return DateUtils.formatTimeZ(date)
    }

    private fun ruleThreeAndInputNumber(dollar: Double, inputNumber: Double, tax: Int): Double {
        val dollarInputNumber = dollar * inputNumber
        return (dollarInputNumber * tax) / 100
    }

    private fun divideInputNumberTax( inputNumber: Double, taxArca: Int): Double {
        return (inputNumber * taxArca) / 100
    }

    private fun formatMount(value: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es"))
        formatter.maximumFractionDigits = 2
        formatter.minimumFractionDigits = 2
        return formatter.format(value)
    }

}