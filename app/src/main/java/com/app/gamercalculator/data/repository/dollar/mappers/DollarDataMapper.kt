package com.app.gamercalculator.data.repository.dollar.mappers

import com.app.gamercalculator.data.database.entities.DollarVO
import com.app.gamercalculator.data.model.DollarResponse
import com.app.gamercalculator.domain.entities.Dollar
import javax.inject.Inject

class DollarDataMapper @Inject constructor() {

    fun mapToVo(dto: DollarResponse): DollarVO {
        return DollarVO(
            id = 0,
            house = dto.house,
            buy = dto.buy,
            date = dto.date,
            money = dto.money,
            name = dto.name,
            sell = dto.sell
        )
    }

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


}