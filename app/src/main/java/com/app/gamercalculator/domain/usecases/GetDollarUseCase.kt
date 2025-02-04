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
            dollarValue = formatMount(dollarValue),
            taxIva = formatMount(taxIva),
            taxArca = formatMount(taxArca),
            mountTotal = formatMount(mountTotal),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarMep(inputNumber: String, moneyCurrency: String): DollarTaxes {
        val dollarMep = repository.getDollarMep()
        val inputAmount = inputNumber.toDouble()

        val countDollarMep = dollarMep.sell * inputAmount
        val countDollarPesos = inputAmount / dollarMep.sell

        return DollarTaxes(
            name = dollarMep.name,
            date = formatDate(dollarMep.date),
            dollarValue = formatMount(when(moneyCurrency){
                "ARS" -> inputAmount
                "USD" -> countDollarMep
                "EUR" -> 0.0
                else -> {0.0}
            }),
            taxIva = "0.00",
            taxArca = "0.00",
            mountTotal =
            formatMount(when(moneyCurrency){
                "ARS" -> countDollarPesos
                "USD" -> countDollarMep
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) countDollarMep else countDollarPesos),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarCripto(inputNumber: String, moneyCurrency: String): DollarTaxes {
        val dollarCripto = repository.getDollarCripto()
        val inputAmount = inputNumber.toDouble()

        val countDollarCripto = dollarCripto.sell * inputAmount
        val countPesosCripto = inputAmount / dollarCripto.sell

        return DollarTaxes(
            name = dollarCripto.name,
            date = formatDate(dollarCripto.date),
            dollarValue =
            formatMount(when(moneyCurrency){
                "ARS" -> inputAmount
                "USD" -> countDollarCripto
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) countDollarCripto else inputAmount),
            taxIva = "0.00",
            taxArca = "0.00",
            mountTotal =
            formatMount(when(moneyCurrency){
                "ARS" -> countPesosCripto
                "USD" -> countDollarCripto
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) countDollarCripto else countPesosCripto),
            mountTotalTaxes = "0.00"
        )
    }

    suspend fun getDollarCardDigital(inputNumber: String, moneyCurrency: String): DollarTaxes {
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
            dollarValue =
            formatMount(when(moneyCurrency){
                "ARS" -> inputAmount
                "USD" -> dollarOfficial.sell * inputAmount
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) dollarOfficial.sell * inputAmount else inputAmount),
            taxIva =
            formatMount(when(moneyCurrency){
                "ARS" -> taxIvaPesos
                "USD" -> taxIvaDollar
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) taxIvaDollar else taxIvaPesos),
            taxArca =
            formatMount(when(moneyCurrency){
                "ARS" -> taxArcaPesos
                "USD" -> taxArcaDollar
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) taxArcaDollar else taxArcaPesos),
            mountTotal =
            formatMount(when(moneyCurrency){
                "ARS" -> sumTaxesPesos
                "USD" -> sumTaxesDollar
                "EUR" -> 0.0
                else -> {0.0}
            }),
            //formatMount(if (isDollarChecked) sumTaxesDollar else sumTaxesPesos),
            mountTotalTaxes =
            formatMount(when(moneyCurrency){
                "ARS" -> sumTaxesPesos
                "USD" -> sumTaxesTotalDollar
                "EUR" -> 0.0
                else -> {0.0}
            })
            //formatMount(if (isDollarChecked) sumTaxesTotalDollar else sumTaxesTotalPesos)
        )
    }


    private fun calculateTax(base: Double, amount: Double, taxPercentage: Int): Double {
        return (base * amount * taxPercentage) / 100
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