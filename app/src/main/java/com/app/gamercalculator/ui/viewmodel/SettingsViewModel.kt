package com.app.gamercalculator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.Settings
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import com.app.gamercalculator.domain.usecases.GetSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getDollarUseCase: GetDollarUseCase, private val getSettingsUseCase: GetSettingsUseCase): ViewModel() {

    private val _dollar = MutableLiveData<Dollar>()
    val dollar: LiveData<Dollar> = _dollar

    private val _settings = MutableLiveData<List<Settings>>()
    val settings: LiveData<List<Settings>> = _settings

    fun getSettings() {
        val data = getSettingsUseCase.getSetting()
        _settings.postValue(data)
    }

}