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
        val inputAmount = inputNumber.toDouble()

        return DollarTaxes(
            name = dollarCard.name,
            date = dollarCard.date,
            dollarValue = formatMount(dollarOfficial.sell * inputAmount),
            taxIva = calculateTax(dollarOfficial.buy, inputAmount, 21),
            taxArca = calculateTax(dollarOfficial.buy, inputAmount, 30),
            mountTotal = formatMount(if (isDollarChecked) dollarCard.sell * inputAmount else dollarCard.sell + inputAmount),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarMep(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarMep = repository.getDollarMep()
        val inputAmount = inputNumber.toDouble()

        return DollarTaxes(
            name = dollarMep.name,
            date = formatDate(dollarMep.date),
            dollarValue = formatMount(if (isDollarChecked) dollarMep.sell * inputAmount else inputAmount),
            taxIva = "0.00",
            taxArca = "0.00",
            mountTotal = formatMount(if (isDollarChecked) inputAmount * dollarMep.sell else inputAmount / dollarMep.sell),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarCripto(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarCripto = repository.getDollarCripto()
        val inputAmount = inputNumber.toDouble()

        return DollarTaxes(
            name = dollarCripto.name,
            date = formatDate(dollarCripto.date),
            dollarValue = formatMount(if (isDollarChecked) dollarCripto.sell * inputAmount else inputAmount),
            taxIva = "0.00",
            taxArca = "0.00",
            mountTotal = formatMount(if (isDollarChecked) inputAmount * dollarCripto.sell else inputAmount / dollarCripto.sell),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarCardDigital(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarOfficial = repository.getDollarOfficial()
        val inputAmount = inputNumber.toDouble()

        val taxIvaDollar = calculateTax(dollarOfficial.sell, inputAmount, 21).toDouble()
        val taxArcaDollar = calculateTax(dollarOfficial.sell, inputAmount, 30).toDouble()
        val totalDollar = dollarOfficial.sell * inputAmount + taxIvaDollar + taxArcaDollar

        val taxIvaPesos = calculateTax(inputAmount, 1.0, 21).toDouble()
        val taxArcaPesos = calculateTax(inputAmount, 1.0, 30).toDouble()
        val totalPesos = inputAmount + taxIvaPesos + taxArcaPesos

        return DollarTaxes(
            name = dollarOfficial.name,
            date = formatDate(dollarOfficial.date),
            dollarValue = formatMount(if (isDollarChecked) dollarOfficial.sell * inputAmount else inputAmount),
            taxIva = formatMount(if (isDollarChecked) taxIvaDollar else taxIvaPesos),
            taxArca = formatMount(if (isDollarChecked) taxArcaDollar else taxArcaPesos),
            mountTotal = formatMount(if (isDollarChecked) totalDollar else totalPesos),
            mountTotalTaxes = formatMount(if (isDollarChecked) taxIvaDollar + taxArcaDollar else taxIvaPesos + taxArcaPesos)
        )
    }

    private fun formatDate(date: String): String {
        return DateUtils.formatTimeZ(date)
    }

    private fun calculateTax(base: Double, amount: Double, taxPercentage: Int): String {
        return formatMount((base * amount * taxPercentage) / 100)
    }

    private fun formatMount(value: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es")).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }
        return formatter.format(value)
    }


}