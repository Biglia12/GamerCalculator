package com.app.gamercalculator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.gamercalculator.R
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.Settings
import com.app.gamercalculator.domain.usecases.GetDollarUseCase
import com.app.gamercalculator.domain.usecases.GetSettingsUseCase
import com.app.gamercalculator.domain.utils.ResourceProvider
import com.app.gamercalculator.domain.utils.ResourceProviderImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getDollarUseCase: GetDollarUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _dollar = MutableLiveData<Dollar>()
    val dollar: LiveData<Dollar> = _dollar

    private val _settings = MutableLiveData<List<Settings>>()
    val settings: LiveData<List<Settings>> = _settings

    fun getSettings() {
        val data = getSettingsUseCase.getSetting()
        _settings.postValue(data)
    }

    fun getSettings1() {
        val data = getSettingsUseCase.getSetting1()

        val translatedData = data.map { setting ->
            val translatedText = resourceProvider.getString(
                when (setting.name) {
                    "share" -> R.string.share
                    "qr" -> R.string.qr
                    "rate" -> R.string.rate
                    "contact" -> R.string.contact
                    "about" -> R.string.about
                    "other_apps" -> R.string.other_apps
                    "announcement" -> R.string.announcement
                    else -> R.string.qr
                }
            )
            setting.copy(name = translatedText)
        }
        _settings.postValue(translatedData)
    }

}