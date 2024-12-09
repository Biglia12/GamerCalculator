package com.app.gamercalculator.data.repository.plataforms

import com.app.gamercalculator.R
import com.app.gamercalculator.domain.entities.Dollar
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.repository.PlataformsRepository
import javax.inject.Inject

class PlataformsDataRepository @Inject constructor() : PlataformsRepository {

     override fun getPlataformsDollar(): List<Plataforms> {
         val plataforms = listOf(
             Plataforms(R.drawable.ic_launcher_background, "Sony", "15"),
             Plataforms(R.drawable.ic_launcher_background, "Steam", "15"),
             Plataforms(R.drawable.ic_launcher_background, "Epic", "15"),
             Plataforms(R.drawable.ic_launcher_background, "Uplay", "15"),
             Plataforms(R.drawable.ic_launcher_background, "Ea", "15")
         )
         return plataforms
     }

     override fun getPlataformsPesos(): List<Plataforms> {
         val plataforms = listOf(
             Plataforms(R.drawable.ic_launcher_background, "Xbox", "15"),
             Plataforms(R.drawable.ic_launcher_background, "Nintendo", "20"),
         )
         return plataforms
     }


}