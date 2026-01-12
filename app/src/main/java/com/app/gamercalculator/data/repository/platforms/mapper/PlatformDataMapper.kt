package com.app.gamercalculator.data.repository.platforms.mapper

import com.app.gamercalculator.data.database.entities.AmountVO
import com.app.gamercalculator.data.database.entities.PlatformVO
import com.app.gamercalculator.data.database.entities.PricePlanVO
import com.app.gamercalculator.data.model.Amount
import com.app.gamercalculator.data.model.Platform
import com.app.gamercalculator.data.model.PlatformDto
import com.app.gamercalculator.data.model.PricePlan
import javax.inject.Inject

class PlatformDataMapper  @Inject constructor() {

    fun mapToVo(dto: PlatformDto): PlatformVO {
        return PlatformVO(
            name = dto.name ?: "",
            imageName = dto.imageName ?: "",
            money = dto.money ?: "",
            prices = dto.prices.orEmpty().map { planDto ->
                PricePlanVO(
                    type = planDto.type ?: "",
                    amounts = planDto.amounts.orEmpty().map { amountDto ->
                        AmountVO(
                            period = amountDto.period ?: "",
                            price = amountDto.price ?: 0.0
                        )
                    }
                )
            }
        )
    }


    fun mapToDomain(vo: PlatformVO): Platform {
        return Platform(
            name = vo.name,
            imageName = vo.imageName,
            money = vo.money,
            prices = vo.prices.map { planVo ->
                PricePlan(
                    type = planVo.type,
                    amounts = planVo.amounts.map { amountVo ->
                        Amount(
                            period = amountVo.period,
                            price = amountVo.price
                        )
                    }
                )
            }
        )
    }


}