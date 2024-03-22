package com.rendonsoft.apptestapi.feature.home.framework.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rendonsoft.apptestapi.R
import com.rendonsoft.apptestapi.commos.util.AUTOS
import com.rendonsoft.apptestapi.commos.util.URL_BASE
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.composables.CardItem
import fetchData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(modifier: Modifier) {

    var responseData by remember { mutableStateOf<List<AutoItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        val url = "$URL_BASE$AUTOS"
        val response = fetchData(url)
        responseData = response
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.toolbar_home))
                },
            )
        }
    ) {
        LazyColumn(modifier = modifier.padding(it)) {
            items(responseData.size) { index ->
                CardItem(
                    item = responseData[index]
                )
            }
        }
    }
}