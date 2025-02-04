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

        val dollarValue = dollarOfficial.sell * inputAmount
        val taxIva = calculateTax(dollarOfficial.buy, inputAmount, 21)
        val taxArca = calculateTax(dollarOfficial.buy, inputAmount, 30)
        val mountTotal = if (isDollarChecked) dollarCard.sell * inputAmount else dollarCard.sell + inputAmount

        return DollarTaxes(
            name = dollarCard.name,
            date = formatDate(dollarCard.date),
            dollarValue = "formatMount(dollarValue)",
            taxIva = "formatMount(taxIva)",
            taxArca = "formatMount(taxArca)",
            mountTotal = "formatMount(mountTotal)",
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarMep(inputNumber: String, moneyCurrency: String): DollarTaxes {
        val dollarMep = repository.getDollarMep()
        return calculateDollarValues(dollarMep.name, dollarMep.sell, dollarMep.date, inputNumber, moneyCurrency)
    }

    suspend fun getDollarCripto(inputNumber: String, moneyCurrency: String): DollarTaxes {
        val dollarCripto = repository.getDollarCripto()
        return calculateDollarValues(dollarCripto.name, dollarCripto.sell, dollarCripto.date, inputNumber, moneyCurrency)
    }

    suspend fun getDollarCardDigital(inputNumber: String, moneyCurrency: String): DollarTaxes {
        val dollarOfficial = repository.getDollarOfficial()
        val inputAmount = inputNumber.toDouble()

        // Impuestos en USD
        val taxIvaDollar = calculateTax(dollarOfficial.sell, inputAmount, 21)
        val taxArcaDollar = calculateTax(dollarOfficial.sell, inputAmount, 30)
        val totalDollarWithTaxes = dollarOfficial.sell * inputAmount + taxIvaDollar + taxArcaDollar
        val totalTaxesDollar = taxIvaDollar + taxArcaDollar

        // Impuestos en ARS
        val taxIvaPesos = calculateTax(inputAmount, 1.0, 21)
        val taxArcaPesos = calculateTax(inputAmount, 1.0, 30)
        val totalPesosWithTaxes = inputAmount + taxIvaPesos + taxArcaPesos
        val totalTaxesPesos = taxIvaPesos + taxArcaPesos

        return DollarTaxes(
            name = dollarOfficial.name,
            date = formatDate(dollarOfficial.date),
            dollarValue = formatAmount(getValueByCurrency(moneyCurrency, inputAmount, dollarOfficial.sell)),
            taxIva = formatAmount(getValueByCurrency(moneyCurrency, taxIvaPesos, taxIvaDollar)),
            taxArca = formatAmount(getValueByCurrency(moneyCurrency, taxArcaPesos, taxArcaDollar)),
            mountTotal = formatAmount(getValueByCurrency(moneyCurrency, totalPesosWithTaxes, totalDollarWithTaxes)),
            mountTotalTaxes = formatAmount(getValueByCurrency(moneyCurrency, totalTaxesPesos, totalTaxesDollar))
        )
    }

    private fun calculateDollarValues(name: String, sellPrice: Double, date: String, inputNumber: String, moneyCurrency: String): DollarTaxes {
        val inputAmount = inputNumber.toDouble()

        val amountInUsd = sellPrice * inputAmount
        val amountInArs = inputAmount / sellPrice

        return DollarTaxes(
            name = name,
            date = formatDate(date),
            dollarValue = formatAmount(getValueByCurrency(moneyCurrency, inputAmount, amountInUsd)),
            taxIva = "0.00",
            taxArca = "0.00",
            mountTotal = formatAmount(getValueByCurrency(moneyCurrency, amountInArs, amountInUsd)),
            mountTotalTaxes = "0.00"
        )
    }

    private fun getValueByCurrency(currency: String, arsValue: Double, usdValue: Double): Double {
        return when (currency) {
            "ARS" -> arsValue
            "USD" -> usdValue
            "EUR" -> 0.0
            else -> 0.0
        }
    }

    private fun calculateTax(base: Double, amount: Double, taxPercentage: Int): Double {
        return (base * amount * taxPercentage) / 100
    }

    private fun formatAmount(value: Double): String {
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