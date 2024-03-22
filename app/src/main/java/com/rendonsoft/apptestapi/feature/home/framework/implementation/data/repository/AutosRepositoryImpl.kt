package com.rendonsoft.apptestapi.feature.home.framework.implementation.data.repository

import com.rendonsoft.apptestapi.feature.home.data.data_source.AutosLocalDataSource
import com.rendonsoft.apptestapi.feature.home.data.data_source.AutosRemoteDataSource
import com.rendonsoft.apptestapi.feature.home.data.repository.AutosRepository
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem

class AutosRepositoryImpl(
    private val dataSourceLocal: AutosLocalDataSource,
    private val dataSourceRemote: AutosRemoteDataSource,
) : AutosRepository {

    override suspend fun getAutos(): List<AutoItem> {
        val autosLocal = dataSourceLocal.getAutos()
        return if (autosLocal.isEmpty()) {
            val autosRemote = dataSourceRemote.getAutos()
            dataSourceLocal.insertAutos(autosRemote.toTypedArray())
            autosRemote.map {
                AutoItem(
                    code = it.code,
                    name = it.name
                )
            }.toList()
        } else {
            autosLocal.map {
                AutoItem(
                    code = it.code,
                    name = it.name
                )
            }.toList()
        }
    }
}