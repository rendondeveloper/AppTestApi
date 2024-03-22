package com.rendonsoft.apptestapi.feature.home.data.data_source

import com.rendonsoft.apptestapi.commos.database.models.AutoDto

interface AutosRemoteDataSource {
    suspend fun getAutos() : List<AutoDto>;
}