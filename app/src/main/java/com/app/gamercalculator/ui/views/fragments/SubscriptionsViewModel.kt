package com.app.gamercalculator.ui.views.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import com.app.gamercalculator.domain.usecases.GetPlatformsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionsViewModel @Inject constructor(
    private val getDollarUseCase: GetDollarUseCase,
    private val getPlatformsUseCase: GetPlatformsUseCase
) : ViewModel() {

    val dollar: LiveData<List<DollarResponse>> get() = _dollar
    private val _dollar = MutableLiveData<List<DollarResponse>>()

    val getAllDollar: LiveData<List<Dollar>> get() = _getAllDollar
    private val _getAllDollar = MutableLiveData<List<Dollar>>()

    val platforms: LiveData<List<Platform>> get() = _platforms
    private val _platforms = MutableLiveData<List<Platform>>()


    fun getDollarFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            getDollarUseCase.getDollarFromApi()
            // val listDollar = getDollarUseCase.getDollar()
            // _dollar.postValue(listDollar)
        }

    }

    fun getPlatforms() {
        viewModelScope.launch {
            val platform = getPlatformsUseCase.getPlatforms()
            _platforms.postValue(platform)
        }
    }


    fun getAllDollar() {
        viewModelScope.launch(Dispatchers.IO) {
            val listDollar = getDollarUseCase.getAllFromDatabase()
            _getAllDollar.postValue(listDollar)
        }
    }
}