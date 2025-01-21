package com.app.gamercalculator.data.repository.settings

import com.app.gamercalculator.R
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.entities.Settings
import com.app.gamercalculator.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsDataRepository  @Inject constructor() : SettingsRepository {
    override fun getSettings(): List<Settings> {
        val settings = listOf(
            Settings(R.drawable.ic_share, "Compartir App"),
            Settings(R.drawable.ic_qr, "Compartir App con QR"),
            Settings(R.drawable.ic_subscription, "Calificar App"),
            Settings(R.drawable.ic_contact, "Contactar"),
            Settings(R.drawable.ic_about, "Acerca De"),
            Settings(R.drawable.ic_apps, "Nuestras Otras Apps"),
            Settings(R.drawable.ic_camera, "Para Ayudarnos Mira Este Breve Anuncio")
        )
        return settings
    }
}