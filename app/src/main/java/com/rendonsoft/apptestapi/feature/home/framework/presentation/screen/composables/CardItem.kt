package com.rendonsoft.apptestapi.feature.home.framework.presentation.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rendonsoft.apptestapi.feature.home.domain.models.AutoItem
import com.rendonsoft.apptestapi.ui.theme.AppTestApiTheme

@Composable
fun CardItem(
    item: AutoItem,
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
            Text(text = item.codigo, style = MaterialTheme.typography.labelMedium)
            Text(text = item.nome, style = MaterialTheme.typography.labelSmall)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CardItemGreetingPreview() {
    AppTestApiTheme {
        CardItem(AutoItem(codigo = "Android", nome =  "20"))
    }
}