package com.example.planty.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.common.composables.PlusFAB
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.navigation.PlantyScreen
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme
import timber.log.Timber

@Composable
fun HomeScreenView(
    viewModel: HomeViewModel,
    onFabClicked: () -> Unit,
    onPlantCardClicked: (id: String) -> Unit
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
        floatingActionButton = { PlusFAB { onFabClicked() } },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { HomeScreenBottomBar(currentScreen = currentScreen) }
    ) {
        Box(modifier = Modifier.padding(it)
        ) {
            LoadingContent(
                initialLoad = uiState.initialLoad,
                initialLoadContent = { HomeLoadingScreen() },
                content = { PlantCardGrid(cards = uiState.plantEntries) }
            )

        }
    }
}

@Composable
private fun LoadingContent(
    initialLoad: Boolean,
    initialLoadContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (initialLoad) {
        initialLoadContent()
    } else {
        content()
    }
}

@Composable
private fun HomeLoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.offset(y = Dimen.Hundred))
        
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.loading_content_image),
                contentDescription = stringResource(R.string.loading_image_description),
                modifier = Modifier.alpha(0.5f),
            )
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
fun DefaultHomeScreenPreview() {
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

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultHomeScreenInitialLoadPreview() {
    val uiState = HomeUiState(
        loading = false,
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
