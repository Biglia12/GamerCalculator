package com.app.gamercalculator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(val getDollarUseCase: GetDollarUseCase): ViewModel() {

    private val _dollar = MutableLiveData<Dollar>()
    val dollar: LiveData<Dollar> = _dollar

}