package com.app.gamercalculator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getDollarUseCase: GetDollarUseCase) : ViewModel() {

    val dollar: LiveData<List<DollarResponse>> get() =  _dollar
    private val _dollar = MutableLiveData<List<DollarResponse>>()

    init {
        //fun getDollar() {
            viewModelScope.launch {
                val listDollar = getDollarUseCase.getDollar()
                _dollar.postValue(listDollar)
            }
      //  }

    }


}