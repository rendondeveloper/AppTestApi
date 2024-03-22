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
import com.rendonsoft.apptestapi.commos.database.models.AutoDto
import com.rendonsoft.apptestapi.commos.util.AUTOS
import com.rendonsoft.apptestapi.commos.util.URL_BASE
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.composables.CardItem
import fetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(modifier: Modifier) {

    var responseData by remember { mutableStateOf<List<AutoItem>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val list = getAutos(context = context)

        if (list.isEmpty()) {
            val url = "$URL_BASE$AUTOS"
            val response = fetchData(url)
            insertUser(response, context)
            responseData = response
        } else {
            responseData = list
        }
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

suspend fun insertUser(
    autos: List<AutoItem>,
    context: Context,
) {
    withContext(Dispatchers.IO) {

        val list = autos.map { item -> AutoDto(name = item.codigo, code = item.codigo) }
            .toTypedArray()
        val db = AppDatabase.getInstance(context)
        db.autoDao()
            .insertAuto(
                autos = list
            )
    }
}

suspend fun getAutos(
    context: Context,
): List<AutoItem> {
    return withContext(Dispatchers.IO) {
        val db = AppDatabase.getInstance(context)
        db.autoDao().getAutos().map {
            AutoItem(
                codigo = it.code,
                nome = it.name
            )
        }.toList()
    }
}