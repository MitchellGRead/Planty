package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.navigation.PlantyScreen
import com.example.planty.ui.theme.PlantyTheme
import timber.log.Timber

@Composable
fun HomeScreenView(
    viewModel: HomeViewModel,
    onFabClicked: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val currBottomNavRoute = remember { mutableStateOf(HomeScreen) }
    val scaffoldState = rememberScaffoldState()

    DisposableEffect(key1 = "onStart") {
        viewModel.onStart()
        onDispose {  }
    }

    HomeScreenView(
        uiState = uiState.value,
        onFabClicked = {
            viewModel.createPlantEntry()
            onFabClicked()
        },
        currentScreen = currBottomNavRoute.value,
        scaffoldState = scaffoldState
    )
}

@Composable
private fun HomeScreenView(
    uiState: HomeUiState,
    onFabClicked: () -> Unit,
    currentScreen: PlantyScreen,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeScreenTopBar() },
        floatingActionButton = { HomeScreenFAB(onFabClicked) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { HomeScreenBottomBar(currentScreen = currentScreen) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            PlantCardGrid(cards = uiState.plantEntries)
        }
    }
}

@Composable
private fun HomeScreenTopBar() {
    InsetAwareTopAppBar(
        title = { Text(text = stringResource(id = R.string.Planty)) },
    )
}

@Composable
private fun HomeScreenFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClick()
            Timber.d("Home Screen FAB Clicked")
        },
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.AddPlantEntry)
        )
    }
}

@Composable
private fun HomeScreenBottomBar(currentScreen: PlantyScreen) {
    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        val bottomNavDests = listOf(
            HomeDest,
            ScheduleDest
        )
        BottomNavigation {
            bottomNavDests.forEach { nav ->
                BottomNavigationItem(
                    selected = nav.dest == currentScreen,
                    onClick = { Timber.d("Route ${nav.dest.route} clicked") },
                    label = { Text(text = stringResource(id = nav.label)) },
                    icon = {
                        Icon(
                            imageVector = nav.icon,
                            contentDescription = stringResource(id = nav.label)
                        )
                    }
                )
            }
        }
    }
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    val plantEntry = PlantEntry(id = "2", "Planty")
    val uiState = HomeUiState(
        loading = false,
        plantEntries = listOf(plantEntry, plantEntry, plantEntry)
    )
    PlantyTheme {
        HomeScreenView(
            uiState = uiState,
            onFabClicked = {},
            currentScreen = HomeScreen,
            scaffoldState = rememberScaffoldState()
        )
    }
}
