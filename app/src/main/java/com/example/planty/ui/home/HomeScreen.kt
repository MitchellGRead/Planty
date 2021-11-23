package com.example.planty.ui.home

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.planty.R
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.navigation.HomeScreenRoute
import com.example.planty.ui.navigation.PlantyRoute
import com.example.planty.ui.navigation.ScheduleScreen
import com.example.planty.ui.theme.Dimen
import timber.log.Timber

@Composable
fun HomeScreenView(
    viewModel: HomeViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()
    val currBottomNavRoute = remember { mutableStateOf(HomeScreenRoute) }
    val scaffoldState = rememberScaffoldState()

    DisposableEffect(key1 = "onStart") {
        viewModel.onStart()
        onDispose {  }
    }

    HomeScreen(
        uiState = uiState.value,
        onFabClicked = { viewModel.createPlantEntry() },
        currentRoute = currBottomNavRoute.value,
        scaffoldState = scaffoldState
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onFabClicked: () -> Unit,
    currentRoute: PlantyRoute,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeScreenTopBar() },
        floatingActionButton = { HomeScreenFAB(onFabClicked) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { HomeScreenBottomBar(currentRoute = currentRoute) }
    ) {
        PlantCardGrid(cards = uiState.plantEntries)
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
fun HomeScreenFAB(onClick: () -> Unit) {
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

//@Preview(name = "default")
//@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun DefaultPreview() {
//    PlantyTheme {
//        HomeScreenView()
//    }
//}
