package com.app.gamercalculator

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun){
    object Calculator: TabItem(R.drawable.ic_calculate, "Calculator", { CalculatorView()})
    object Configuration: TabItem(R.drawable.ic_settings, "Configuration", { ConfigurationView() })
}
