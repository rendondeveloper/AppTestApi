package com.rendonsoft.apptestapi.feature.home.framework.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rendonsoft.apptestapi.R.string
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.composables.CardItem
import com.rendonsoft.apptestapi.feature.home.framework.presentation.view_model.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HomeViewModel,
    modifier: Modifier,
) {

    val autosData by viewModel.autos.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchAutos()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = string.toolbar_home))
                },
            )
        }
    ) {
        LazyColumn(modifier = modifier.padding(it)) {
            items(autosData.size) { index ->
                CardItem(
                    item = autosData[index]
                )
            }
        }
    }
}