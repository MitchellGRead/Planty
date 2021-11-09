package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun HomeScreen(name: String) {
    val card = PlantEntry(id = "1", name = "Planty")
    val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)

    PlantCardGrid(cards = cards)
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PlantyTheme {
        HomeScreen("Android")
    }
}
