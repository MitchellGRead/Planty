package com.example.planty.ui.createEntry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.planty.R
import com.example.planty.ui.common.composables.PlantyTopBar
import com.example.planty.ui.navigation.HomeScreenRoute

@Composable
fun CreatePlantyView(viewModel: CreatePlantyViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    val currBottomNavRoute = remember { mutableStateOf(HomeScreenRoute) }
    val scaffoldState = rememberScaffoldState()

    CreatePlantyView(
        uiState = uiState.value,
        scaffoldState = scaffoldState
    )
}

@Composable
fun CreatePlantyView(
    uiState: CreatePlantyUiState,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { CreatePlantyTopBar() }
    ) {
        Box(modifier = Modifier.padding(it)) {
            CreateEntryCard()
        }
    }
}

@Composable
fun CreatePlantyTopBar() {
    PlantyTopBar(title = R.string.Create_Entry)
}

@Composable
fun CreateEntryCard() {
    Card(modifier = Modifier.padding()) {

    }
}
