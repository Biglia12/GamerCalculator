package com.app.gamercalculator.domain.repository

import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.entities.Settings

interface SettingsRepository {
    fun getSettings1(): List<Settings>
    fun getSettings(): List<Settings>
}