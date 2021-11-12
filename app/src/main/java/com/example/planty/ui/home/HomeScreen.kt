package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.navigation.HomeScreenRoute
import com.example.planty.ui.navigation.PlantyRoute
import com.example.planty.ui.navigation.ScheduleScreen
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme
import timber.log.Timber

@Composable
fun HomeScreenView(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val card = PlantEntry(id = "1", name = "Planty")
    val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)
    val currBottomNavRoute = remember { mutableStateOf(HomeScreenRoute) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeScreenTopBar() },
        floatingActionButton = { HomeScreenFAB() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { HomeScreenBottomBar(currBottomNavRoute.value) }
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
fun HomeScreenBottomBar(currentRoute: PlantyRoute) {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        val bottomNavDests = listOf(
            HomeScreen,
            ScheduleScreen
        )
        BottomNavigation {
            bottomNavDests.forEach { screen ->
                BottomNavigationItem(
                    selected = screen.dest == currentRoute,
                    onClick = { Timber.d("Route ${screen.dest.route} clicked") },
                    label = { screen.label?.let { Text(text = it) }},
                    icon = { screen.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = stringResource(id = R.string.Home)
                        )
                    }}
                )
            }
        }
    }
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PlantyTheme {
        HomeScreenView()
    }
}
