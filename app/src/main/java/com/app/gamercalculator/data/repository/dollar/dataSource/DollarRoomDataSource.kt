package com.app.gamercalculator.data.repository.dollar.dataSource

import com.app.gamercalculator.data.database.AppDataBase
import com.app.gamercalculator.data.database.entities.DollarVO
import javax.inject.Inject

class DollarRoomDataSource @Inject constructor(private val db: AppDataBase) {

    suspend fun insert(dollar: List<DollarVO>){
        db.dollarDao().insert(dollar)
    }

    suspend fun getAll(): List<DollarVO>{
        return db.dollarDao().getAll()
    }

    suspend fun getDollarOfficial(): DollarVO{
        return db.dollarDao().getDollarOfficial()
    }

    suspend fun getDollarCard(): DollarVO{
        return db.dollarDao().getDollarCard()
    }

    suspend fun getDollarMep(): DollarVO{
        return db.dollarDao().getDollarMep()
    }

    suspend fun cleanTable(){
         db.dollarDao().delete()
    }
}