package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val card = PlantEntry(id = "1", name = "Planty")
    val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeScreenTopBar() }
    ) {
        PlantCardGrid(cards = cards)
    }
}

@Composable
fun HomeScreenTopBar() {
    InsetAwareTopAppBar(
        title = { Text(stringResource(R.string.Planty)) },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = Dimen.Zero
    )
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PlantyTheme {
        HomeScreen()
    }
}
