package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme
import com.example.planty.ui.theme.Shapes
import com.example.planty.ui.theme.Typography


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantCardGrid(cards: List<PlantEntry>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
    ) {
        items(cards.size) { idx ->
            val card = cards[idx]
            PlantCard(title = card.name)
        }
    }
}

@Composable
fun PlantCard(title: String) {
    Card(
        shape = Shapes.large,
        modifier = Modifier
            .padding(Dimen.L)
            .fillMaxWidth()
            .clickable { }
    ) {
        Column {
            Column (
                modifier = Modifier.padding(Dimen.L)
            ){
                Text(
                    text = title,
                    textAlign = TextAlign.Left,
                    style = Typography.h6
                )
                Text(
                    text = "description",
                    textAlign = TextAlign.Left,
                    style = Typography.body1
                )
            }
            Image(
                painter = painterResource(id = R.drawable.placeholder2),
                contentDescription = "plant",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimen.Hundred)
            )
        }
    }
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PlantCardPreview() {
    PlantyTheme {
        PlantCard(title = "title")
    }
}

@ExperimentalFoundationApi
@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PlantCardGridPreview() {
    val card = PlantEntry(
        id = "1",
        name = "I am plant"
    )
    val cards = listOf(card, card, card, card, card)
    PlantyTheme {
        PlantCardGrid(cards = cards)
    }
}
