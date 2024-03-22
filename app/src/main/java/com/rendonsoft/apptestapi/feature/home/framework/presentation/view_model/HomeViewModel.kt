package com.rendonsoft.apptestapi.feature.home.framework.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendonsoft.apptestapi.feature.home.data.repository.AutosRepository
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.state.HomeUiState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repositoryImpl: AutosRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val state: StateFlow<HomeUiState> = _state

    init {
        fetchAutos();
    }

    private fun fetchAutos() {
        viewModelScope.launch(IO) {
            //Add delay to show loading because de answer from api and database is faster
            delay(5_000)
            val autosList =  repositoryImpl.getAutos()
            _state.update {
                HomeUiState.Data(
                    autos = autosList
                )
            }
        }
    }
}