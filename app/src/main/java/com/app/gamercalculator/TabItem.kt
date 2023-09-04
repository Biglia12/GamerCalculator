package com.app.gamercalculator

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(var icon: Int, var title: Int, var screen: ComposableFun){
    object Calculator: TabItem(R.drawable.ic_calculate, R.string.calculator, { CalculatorView()})
    object Configuration: TabItem(R.drawable.ic_settings, R.string.settings, { ConfigurationView() })
}
