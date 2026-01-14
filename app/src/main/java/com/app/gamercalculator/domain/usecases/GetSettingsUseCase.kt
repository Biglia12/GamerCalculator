package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.data.repository.settings.SettingsDataRepository
import com.app.gamercalculator.domain.entities.Settings
import javax.inject.Inject

class GetSettingsUseCase  @Inject constructor (val settingsDataRepository: SettingsDataRepository){

    fun getSetting1(): List<Settings> {
        return settingsDataRepository.getSettings1()
    }

    fun getSetting(): List<Settings> {
        return settingsDataRepository.getSettings()
    }

}