package com.app.gamercalculator.domain.usecases

import com.app.gamercalculator.R
import com.app.gamercalculator.domain.entities.Plataforms
import javax.inject.Inject

class GetPlataformsUseCase @Inject constructor() {
    suspend fun getPlataformsDollar(): List<Plataforms> {
        val plataforms = listOf(
            Plataforms(R.drawable.ic_launcher_background, "Sony", "15"),
            Plataforms(R.drawable.ic_launcher_background, "Steam", "15"),
            Plataforms(R.drawable.ic_launcher_background, "Epic", "15"),
            Plataforms(R.drawable.ic_launcher_background, "Uplay", "15"),
            Plataforms(R.drawable.ic_launcher_background, "Ea", "15")
        )
        return plataforms
    }

    suspend fun getPlataformsPesos(): List<Plataforms> {
        val plataforms = listOf(
            Plataforms(R.drawable.ic_launcher_background, "Xbox", "15"),
            Plataforms(R.drawable.ic_launcher_background, "Nintendo", "20"),
        )
        return plataforms
    }

}