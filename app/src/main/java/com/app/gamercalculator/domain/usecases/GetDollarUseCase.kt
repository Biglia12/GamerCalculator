package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.DollarTaxes
import com.app.gamercalculator.domain.repository.DollarRepository
import com.app.gamercalculator.domain.utils.DateUtils
import java.text.NumberFormat
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

        val dollarValue = dollarOfficial.buy * inputAmount
        val taxIva = calculateTax(dollarOfficial.buy, inputAmount, 21)
        val taxArca = calculateTax(dollarOfficial.buy, inputAmount, 30)
        val mountTotal = if (isDollarChecked) dollarCard.sell * inputAmount else dollarCard.sell + inputAmount

        return DollarTaxes(
            name = dollarCard.name,
            date = formatDate(dollarCard.date),
            dollarValue = formatMount(dollarValue),
            taxIva = formatMount(taxIva),
            taxArca = formatMount(taxArca),
            mountTotal = formatMount(mountTotal),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarMep(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarMep = repository.getDollarMep()
        val inputAmount = inputNumber.toDouble()

        val countDollarMepWithOutTaxes = dollarMep.sell * inputAmount
        val taxIva = calculateTax(dollarMep.sell, inputAmount, 21)
        val mountTotalWithIvaDollar = countDollarMepWithOutTaxes + taxIva

        val taxIvaPesos = calculateTax(inputAmount, 21)
        val mountTotalWithIvaPesos = inputAmount + taxIvaPesos

        return DollarTaxes(
            name = dollarMep.name,
            date = formatDate(dollarMep.date),
            dollarValue = formatMount(if (isDollarChecked) countDollarMepWithOutTaxes else inputAmount),
            taxIva = if (isDollarChecked) formatMount(taxIva) else formatMount(taxIvaPesos),
            taxArca = "0.00",
            mountTotal = formatMount(if (isDollarChecked) mountTotalWithIvaDollar else mountTotalWithIvaPesos),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarCripto(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarCripto = repository.getDollarCripto()
        val inputAmount = inputNumber.toDouble()

        val countDollarCripto = dollarCripto.sell * inputAmount

        return DollarTaxes(
            name = dollarCripto.name,
            date = formatDate(dollarCripto.date),
            dollarValue = formatMount(if (isDollarChecked) countDollarCripto else inputAmount),
            taxIva = "0.00",
            taxArca = "0.00",
            mountTotal = formatMount(if (isDollarChecked) countDollarCripto else inputAmount),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarCardDigital(inputNumber: String, isDollarChecked: Boolean): DollarTaxes {
        val dollarOfficial = repository.getDollarOfficial()
        val inputAmount = inputNumber.toDouble()

        // Cálculo en dólares
        val taxIvaDollar = calculateTax(dollarOfficial.sell, inputAmount, 21)
        val taxArcaDollar = calculateTax(dollarOfficial.sell, inputAmount, 30)
        val sumTaxesDollar = dollarOfficial.sell * inputAmount + taxIvaDollar + taxArcaDollar
        val sumTaxesTotalDollar = taxIvaDollar + taxArcaDollar

        // Cálculo en pesos
        val taxIvaPesos = calculateTax(inputAmount, 1.0, 21)
        val taxArcaPesos = calculateTax(inputAmount, 1.0, 30)
        val sumTaxesPesos = inputAmount + taxIvaPesos + taxArcaPesos
        val sumTaxesTotalPesos = taxIvaPesos + taxArcaPesos

        return DollarTaxes(
            name = dollarOfficial.name,
            date = formatDate(dollarOfficial.date),
            dollarValue = formatMount(if (isDollarChecked) dollarOfficial.sell * inputAmount else inputAmount),
            taxIva = formatMount(if (isDollarChecked) taxIvaDollar else taxIvaPesos),
            taxArca = formatMount(if (isDollarChecked) taxArcaDollar else taxArcaPesos),
            mountTotal = formatMount(if (isDollarChecked) sumTaxesDollar else sumTaxesPesos),
            mountTotalTaxes = formatMount(if (isDollarChecked) sumTaxesTotalDollar else sumTaxesTotalPesos)
        )
    }


    private fun calculateTax(base: Double, amount: Double, taxPercentage: Int): Double {
        return (base * amount * taxPercentage) / 100
    }

    private fun calculateTax(amount: Double, taxPercentage: Int): Double {
        return (amount * taxPercentage) / 100
    }


    private fun formatMount(value: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es")).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }
        return formatter.format(value)
    }

    private fun formatDate(date: String): String {
        return DateUtils.formatTimeZ(date)
    }



}