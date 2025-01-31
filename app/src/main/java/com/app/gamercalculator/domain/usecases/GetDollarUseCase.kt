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
            dollarValue = formatMount(dollarOfficial.sell * inputNumber.toDouble()),
            taxIva = ruleThreeAndInputNumber(dollarOfficial.buy, inputNumber.toDouble(), 21),
            taxArca = ruleThreeAndInputNumber(dollarOfficial.buy, inputNumber.toDouble(), 30),
            mountTotal = if (isDollarChecked) formatMount(dollarCard.sell.toInt() * inputNumber.toDouble()) else formatMount(
                dollarCard.sell.toInt() + inputNumber.toDouble()
            ),
            0.0.toString()
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
            dollarValue = if (isDollarChecked) formatMount(dollarMep.sell * inputNumber.toDouble()) else formatMount(
                inputNumber.toDouble()
            ),
            taxIva = 0.0.toString(),
            taxArca = 0.0.toString(),
            mountTotal = if (isDollarChecked) countDollarMep else countDollarPesos,
            0.0.toString()
        )
        return dollarMepWitTaxes
    }

    suspend fun getDollarCripto(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarCripto = repository.getDollarCripto()
        val countDollarCripto = formatMount(inputNumber.toDouble() * dollarCripto.sell)
        val countPesosCripto = formatMount(inputNumber.toDouble() / dollarCripto.sell)
        val dollarCriptoWitTaxes = DollarTaxes(
            name = dollarCripto.name,
            date = formateDate(dollarCripto.date),
            dollarValue = if (isDollarChecked) formatMount(dollarCripto.sell * inputNumber.toDouble()) else formatMount(
                inputNumber.toDouble()
            ),
            taxIva = 0.0.toString(),
            taxArca = 0.0.toString(),
            mountTotal = if (isDollarChecked) countDollarCripto else countPesosCripto,
            mountTotalTaxes = 0.0.toString()
        )
        return dollarCriptoWitTaxes
    }


    suspend fun getDollarCardDigital(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarOfficial = repository.getDollarOfficial()
        val taxIvaDollar = ruleThreeAndInputNumber(dollarOfficial.sell, inputNumber.toDouble(), 21)
        val taxArcaDollar = ruleThreeAndInputNumber(dollarOfficial.sell, inputNumber.toDouble(), 30)
        val sumTaxesDollar =
            formatMount(taxIvaDollar.toDouble() + taxArcaDollar.toDouble() + dollarOfficial.sell * inputNumber.toDouble())

        val taxIvaPesos = divideInputNumberTax(inputNumber.toDouble(), 21)
        val taxArcaPesos = divideInputNumberTax(inputNumber.toDouble(), 30)
        val sumTaxesPesos =
            formatMount(taxIvaPesos.toDouble() + taxArcaPesos.toDouble() + inputNumber.toDouble())

        val sumTaxesTotalDollar = formatMount(taxArcaDollar.toDouble() + taxIvaDollar.toDouble())
        val sumTaxesTotalPesos = formatMount(taxArcaPesos.toDouble() + taxIvaPesos.toDouble())

        val dollarCardWitTaxes = DollarTaxes(
            name = dollarOfficial.name,
            date = formateDate(dollarOfficial.date),
            dollarValue = if (isDollarChecked) formatMount(dollarOfficial.sell * inputNumber.toDouble()) else formatMount(
                inputNumber.toDouble()
            ),
            taxIva = ((if (isDollarChecked) formatMount(taxIvaDollar.toDouble()) else formatMount(
                taxIvaPesos.toDouble()
            ))),
            taxArca = if (isDollarChecked) formatMount(taxArcaDollar.toDouble()) else formatMount(
                taxArcaPesos.toDouble()
            ),
            mountTotal = if (isDollarChecked) sumTaxesDollar else sumTaxesPesos,
            mountTotalTaxes = if (isDollarChecked) sumTaxesTotalDollar else sumTaxesTotalPesos
        )
        return dollarCardWitTaxes
    }

    private fun formateDate(date: String): String {
        return DateUtils.formatTimeZ(date)
    }

    private fun ruleThreeAndInputNumber(dollar: Double, inputNumber: Double, tax: Int): String {
        val dollarInputNumber = dollar * inputNumber
        val result = (dollarInputNumber * tax) / 100
        return result.toString()
    }

    private fun divideInputNumberTax(inputNumber: Double, taxArca: Int): String {
        val result = (inputNumber * taxArca) / 100
        return result.toString()
    }

    private fun formatMount(value: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es"))
        formatter.maximumFractionDigits = 2
        formatter.minimumFractionDigits = 2
        return formatter.format(value)
    }

}