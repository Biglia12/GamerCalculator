package com.app.gamercalculator.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.DollarTaxes
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

    val dollarCard: LiveData<DollarTaxes> get() = _dollarCard
    private val _dollarCard = MutableLiveData<DollarTaxes>()

    val dollarMep: LiveData<DollarTaxes> get() = _dollarMep
    private val _dollarMep = MutableLiveData<DollarTaxes>()

    val dollarCripto: LiveData<DollarTaxes> get() = _dollarCripto
    private val _dollarCripto = MutableLiveData<DollarTaxes>()

    val isLoading: MutableLiveData<Boolean> get() = _isLoading
    private val _isLoading = MutableLiveData<Boolean>()

    private var isDataLoaded = false

    fun getDollarFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isDataLoaded) {
                isDataLoaded = true
                _isLoading.postValue(true) // Activa el loading
                val result = kotlin.runCatching {
                    getDollarUseCase.getDollarFromApi() // Llama al caso de uso
                }
                _isLoading.postValue(false) // Desactiva el loading

                result.onFailure { exception ->
                    Log.e("Error", "Error fetching dollar: ${exception.message}")
                }
                Log.i("SuccessHomeViewmodel", "Success fetching dollar")
            }

        }

    }

    fun getPlataformsDollar() {
        viewModelScope.launch {
            val plataformsDollar = getPlataformsUseCase.getPlataformsDollar()
            _plataformsDollar.postValue(plataformsDollar)
            //  isLoading.postValue(false)
        }
    }

    fun getDollarCardDigital(inputNumber: String, isDollarChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val dollarCard = getDollarUseCase.getDollarCardDigital(inputNumber, isDollarChecked)
            Log.i("dollarTarjeta", "$inputNumber $dollarCard")
            _dollarCard.postValue(dollarCard)
            // isLoading.postValue(false)
        }
    }

    fun getDollarMep(inputNumber: String, isDollarChecked: Boolean) {
         viewModelScope.launch(Dispatchers.IO) {
             val dollarMep = getDollarUseCase.getDollarMep(inputNumber, isDollarChecked)
             Log.i("dollarMep", "$inputNumber $dollarMep")
             _dollarMep.postValue(dollarMep)
         }
    }

    fun getDollarCripto(inputNumber: String, isDollarChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val dollarCripto = getDollarUseCase.getDollarCripto(inputNumber, isDollarChecked)
            _dollarCripto.postValue(dollarCripto)
        }
    }

    fun getAllDollar() {
        viewModelScope.launch(Dispatchers.IO) {
            val listDollar = getDollarUseCase.getAllFromDatabase()
            _getAllDollar.postValue(listDollar)
        }
    }
}
