package com.rendonsoft.apptestapi.feature.home.data.data_source

import com.rendonsoft.apptestapi.commos.database.models.AutoDto

interface AutosLocalDataSource {
    suspend fun getAutos() : List<AutoDto>;
    suspend fun insertAutos(list: Array<AutoDto>);
}