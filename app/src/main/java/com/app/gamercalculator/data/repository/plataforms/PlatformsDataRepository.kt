package com.app.gamercalculator.data.repository.plataforms

import com.app.gamercalculator.R
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.data.repository.dollar.mappers.DollarDataMapper
import com.app.gamercalculator.data.repository.plataforms.dataSource.PlatformCloudDataSource
import com.app.gamercalculator.domain.entities.Plataforms
import com.app.gamercalculator.domain.repository.PlataformsRepository
import com.app.gamercalculator.utils.JsonFileReader
import javax.inject.Inject

class PlatformsDataRepository @Inject constructor(val cloudDataSource: PlatformCloudDataSource, val jsonFileReader: JsonFileReader) : PlataformsRepository {


    override suspend fun getPlataforms(): List<Platform> {
        val response = cloudDataSource.getPlatforms()
        //val data = response.map { dataMapper.mapToVo(it) }
        /*roomDataSource.cleanTable()
        roomDataSource.insert(data)*/
        //return response.toList()
        return jsonFileReader.readJsonFromAssets("platforms.json")
    }

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