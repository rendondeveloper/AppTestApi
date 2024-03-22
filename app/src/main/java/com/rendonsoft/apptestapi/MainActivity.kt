package com.rendonsoft.apptestapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.HomePage
import com.rendonsoft.apptestapi.feature.home.framework.presentation.view_model.HomeViewModel
import com.rendonsoft.apptestapi.ui.theme.AppTestApiTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

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







