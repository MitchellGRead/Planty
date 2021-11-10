package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.navigation.PlantyDestinations
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme
import timber.log.Timber

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val card = PlantEntry(id = "1", name = "Planty")
    val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)
    val currentBottomNavDest = remember { mutableStateOf(PlantyDestinations.HOME_SCREEN_ROUTE) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeScreenTopBar() },
        floatingActionButton = { HomeScreenFAB() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { HomeScreenBottomBar(currentBottomNavDest.value) }
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

@Composable
fun HomeScreenFAB() {
    FloatingActionButton(
        onClick = { Timber.d("FAB Clicked") },
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.AddPlantEntry)
        )
    }
}

@Composable
fun HomeScreenBottomBar(currentDest: String) {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        BottomNavigation {
            BottomNavigationItem(
                selected = currentDest == PlantyDestinations.HOME_SCREEN_ROUTE,
                onClick = { Timber.d("I was clicked") },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.Home)
                    )
                },
                label = { Text(text = stringResource(id = R.string.Home)) }
            )

            BottomNavigationItem(
                selected = currentDest == PlantyDestinations.HOME_SCREEN_ROUTE,
                onClick = { Timber.d("I was clicked") },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.Home)
                    )
                },
                label = { Text(text = stringResource(id = R.string.Home)) }
            )
        }
    }
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PlantyTheme {
        HomeScreen()
    }
}
