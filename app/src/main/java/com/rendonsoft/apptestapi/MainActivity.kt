package com.rendonsoft.apptestapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rendonsoft.apptestapi.commos.database.database.AppDatabase
import com.rendonsoft.apptestapi.commos.network.Network
import com.rendonsoft.apptestapi.commos.util.URL_BASE
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source.AutosLocalDataSourceImpl
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.data_source.AutosRemoteDataSourceImpl
import com.rendonsoft.apptestapi.feature.home.framework.implementation.data.repository.AutosRepositoryImpl
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.HomePage
import com.rendonsoft.apptestapi.feature.home.framework.presentation.view_model.HomeViewModel
import com.rendonsoft.apptestapi.ui.theme.AppTestApiTheme

class MainActivity : ComponentActivity() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val repository = AutosRepositoryImpl(
                AutosLocalDataSourceImpl(AppDatabase.getInstance(LocalContext.current)),
                AutosRemoteDataSourceImpl(Network(URL_BASE))
            )

            homeViewModel = HomeViewModel(repository)

            AppTestApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(viewModel = homeViewModel, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}







