package com.rendonsoft.apptestapi.feature.home.framework.di

import com.rendonsoft.apptestapi.feature.home.data.data_source.AutosLocalDataSource
import com.rendonsoft.apptestapi.feature.home.data.data_source.AutosRemoteDataSource
import com.rendonsoft.apptestapi.feature.home.data.repository.AutosRepository
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source.AutosLocalDataSourceImpl
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source.AutosRemoteDataSourceImpl
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.repository.AutosRepositoryImpl
import com.rendonsoft.apptestapi.feature.home.framework.presentation.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    factory<AutosLocalDataSource> {
        AutosLocalDataSourceImpl(
            get(),
        )
    }

    factory<AutosRemoteDataSource> {
        AutosRemoteDataSourceImpl(
            get(),
        )
    }

    factory<AutosRepository> {
        AutosRepositoryImpl(
            get(),
            get(),
        )
    }

    viewModel {
        HomeViewModel(get())
    }
}