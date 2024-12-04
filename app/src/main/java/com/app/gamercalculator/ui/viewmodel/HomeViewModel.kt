package com.app.gamercalculator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import com.app.gamercalculator.domain.usecases.GetPlataformsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDollarUseCase: GetDollarUseCase,
    private val getPlataformsUseCase: GetPlataformsUseCase
) : ViewModel() {

    val dollar: LiveData<List<DollarResponse>> get() = _dollar
    private val _dollar = MutableLiveData<List<DollarResponse>>()

    val plataformsDollar: LiveData<List<Plataforms>> get() = _plataformsDollar
    private val _plataformsDollar = MutableLiveData<List<Plataforms>>()

    val plataformsPesos: LiveData<List<Plataforms>> get() = _plataformsPesos
    private val _plataformsPesos = MutableLiveData<List<Plataforms>>()

    val getAllDollar: LiveData<List<Dollar>> get() = _getAllDollar
    private val _getAllDollar = MutableLiveData<List<Dollar>>()


    fun getDollarFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            getDollarUseCase.getDollarFromApi()
            // val listDollar = getDollarUseCase.getDollar()
            // _dollar.postValue(listDollar)
        }

    }

    fun getPlataformsDollar() {
        viewModelScope.launch {
            val plataformsDollar = getPlataformsUseCase.getPlataformsDollar()
            _plataformsDollar.postValue(plataformsDollar)
        }
    }

    fun getAllDollar() {
        viewModelScope.launch(Dispatchers.IO) {
            val listDollar = getDollarUseCase.getAllFromDatabase()
            _getAllDollar.postValue(listDollar)
        }
    }

}