package com.rendonsoft.apptestapi.feature.home.framework.presentation.screen

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.rendonsoft.apptestapi.R
import com.rendonsoft.apptestapi.commos.database.database.AppDatabase
import com.rendonsoft.apptestapi.commos.network.Network
import com.rendonsoft.apptestapi.commos.util.URL_BASE
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source.AutosLocalDataSourceImpl
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source.AutosRemoteDataSourceImpl
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.repository.AutosRepositoryImpl
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.composables.CardItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(modifier: Modifier) {

    var autosData by remember { mutableStateOf<List<AutoItem>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        autosData = getData(context = context)
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
            items(autosData.size) { index ->
                CardItem(
                    item = autosData[index]
                )
            }
        }
    }
}

suspend fun getData(
    context: Context,
): List<AutoItem> {
    return withContext(Dispatchers.IO) {
        val repository = AutosRepositoryImpl(
            AutosLocalDataSourceImpl(AppDatabase.getInstance(context)),
            AutosRemoteDataSourceImpl(Network(URL_BASE))
        )
        val listNew = repository.getAutos()
        listNew
    }
}