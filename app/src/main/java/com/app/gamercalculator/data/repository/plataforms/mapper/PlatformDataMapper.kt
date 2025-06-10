package com.app.gamercalculator.data.repository.plataforms.mapper

import com.app.gamercalculator.data.database.entities.DollarVO
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.data.model.PlatformDto
import com.app.gamercalculator.domain.entities.Dollar

class PlatformDataMapper {

  /*  fun mapToVo(dto: PlatformDto): DollarVO {
        return DollarVO(

        )
    }*/

    fun map (vo: DollarVO): Dollar {
        return Dollar(
            house = vo.house,
            buy = vo.buy,
            date = vo.date,
            money = vo.money,
            name = vo.name,
            sell = vo.sell
        )
    }

   /* fun mapModel(vo: PlatformDto): Platform{
        return Platform(
            imageName = vo.imageName,
            name = vo.name,
            prices = vo.prices.map {  },
            money = vo.money,
        )
    }*/

    fun mapToDto(vo: DollarVO): DollarResponse {
        return DollarResponse(
            house = vo.house,
            buy = vo.buy,
            date = vo.date,
            money = vo.money,
            name = vo.name,
            sell = vo.sell
        )
    }

}