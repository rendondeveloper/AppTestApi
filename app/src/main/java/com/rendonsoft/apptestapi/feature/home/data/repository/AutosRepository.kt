package com.rendonsoft.apptestapi.feature.home.data.repository

import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem

interface AutosRepository {
    suspend fun getAutos() : List<AutoItem>;
}