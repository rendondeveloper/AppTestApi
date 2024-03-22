package com.rendonsoft.apptestapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rendonsoft.apptestapi.commos.util.AUTOS
import com.rendonsoft.apptestapi.commos.util.URL_BASE
import com.rendonsoft.apptestapi.ui.theme.AppTestApiTheme
import fetchData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTestApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(modifier: Modifier){

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
                    Text(text = "Home")
                },
            )
        }
    ) {
        LazyColumn(modifier = modifier.padding(it)) {
            items(responseData.size) { index ->
                CardItem(
                    responseData[index].codigo,
                    responseData[index].nome,
                );
            }
        }
    }
}

@Composable
fun CardItem(
    name: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Card (
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(text = name, style = MaterialTheme.typography.labelMedium)
            Text(text = price, style = MaterialTheme.typography.labelSmall)
        }

    }

}


@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTestApiTheme {
        Greeting("Android")
    }
}


@Preview(showBackground = true)
@Composable
fun CardItemGreetingPreview() {
    AppTestApiTheme {
        CardItem("Android", "20")
    }
}