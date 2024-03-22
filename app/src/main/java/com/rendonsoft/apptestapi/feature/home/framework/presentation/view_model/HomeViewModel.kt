package com.rendonsoft.apptestapi.feature.home.framework.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendonsoft.apptestapi.feature.home.data.repository.AutosRepository
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repositoryImpl: AutosRepository
) : ViewModel() {

    private val _autos = MutableStateFlow<List<AutoItem>>(emptyList())
    val autos: StateFlow<List<AutoItem>> = _autos

    fun fetchAutos() {
        viewModelScope.launch(IO) {
            val autosList =  repositoryImpl.getAutos()
            _autos.value = autosList
        }
    }
}