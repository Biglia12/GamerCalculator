package com.app.gamercalculator.data.repository.settings

import com.app.gamercalculator.R
import com.app.gamercalculator.domain.entities.Settings
import com.app.gamercalculator.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsDataRepository  @Inject constructor() : SettingsRepository {

    override fun getSettings1(): List<Settings> {
        val settings = listOf(
            Settings(R.drawable.ic_share, "share"),
            Settings(R.drawable.ic_qr, "qr"),
            Settings(R.drawable.ic_subscription, "rate"),
            Settings(R.drawable.ic_contact, "contact"),
            Settings(R.drawable.ic_apps, "other_apps"),
            Settings(R.drawable.ic_camera, "announcement"),
            Settings(R.drawable.ic_about, "about"),
        )
        return settings
    }

    override fun getSettings(): List<Settings> {
       // val settings = listOf(
       //     Settings(R.drawable.ic_share, R.string.share),
       //     Settings(R.drawable.ic_qr, R.string.qr),
       //     Settings(R.drawable.ic_subscription, "Calificar App"),
       //     Settings(R.drawable.ic_contact, "Contactar"),
       //     Settings(R.drawable.ic_about, "Acerca De"),
       //     Settings(R.drawable.ic_apps, "Nuestras Otras Apps"),
       //     Settings(R.drawable.ic_camera, "Para Ayudarnos Mira Este Breve Anuncio")
       // )
        return emptyList()
        //return settings
    }
}