package com.app.gamercalculator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class HomeViewModel /*@Inject constructor*/(private val getDollarUseCase: GetDollarUseCase) : ViewModel() {


    fun getDollar() {
        viewModelScope.launch {
            getDollarUseCase.getDollar()
        }
    }

}