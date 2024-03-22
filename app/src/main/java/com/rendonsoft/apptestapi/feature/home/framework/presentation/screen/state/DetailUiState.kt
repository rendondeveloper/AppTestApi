package com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.state

import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Data(val autos: List<AutoItem>) : HomeUiState
}