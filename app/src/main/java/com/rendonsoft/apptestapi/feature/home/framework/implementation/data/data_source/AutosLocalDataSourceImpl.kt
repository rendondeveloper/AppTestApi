package com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source

import com.rendonsoft.apptestapi.commos.database.database.AppDatabase
import com.rendonsoft.apptestapi.commos.database.models.AutoDto
import com.rendonsoft.apptestapi.feature.home.data.data_source.AutosLocalDataSource

class AutosLocalDataSourceImpl(
    private val database: AppDatabase
) : AutosLocalDataSource {
    override suspend fun getAutos() : List<AutoDto> {
        return database.autoDao().getAutos();
    }

    override suspend fun insertAutos(list: Array<AutoDto>) {
        database.autoDao().insertAuto(autos = list);
    }
}