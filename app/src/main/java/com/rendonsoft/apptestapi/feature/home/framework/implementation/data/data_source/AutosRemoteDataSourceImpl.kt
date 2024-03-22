package com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source

import com.rendonsoft.apptestapi.commos.database.models.AutoDto
import com.rendonsoft.apptestapi.commos.network.Network
import com.rendonsoft.apptestapi.feature.home.data.data_source.AutosRemoteDataSource
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.config.AutoApi

class AutosRemoteDataSourceImpl(
    private val network: Network,
) : AutosRemoteDataSource {
    override suspend fun getAutos(): List<AutoDto> {
        val response = network.getInstance().create(AutoApi::class.java).getAutoAsync().await()

        return try {
            if (response.isSuccessful) {
                response.body()?.map {
                    AutoDto(
                        name = it.nome,
                        code = it.codigo
                    )
                } ?: listOf()
            } else {
                listOf()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            listOf()
        }
    }
}