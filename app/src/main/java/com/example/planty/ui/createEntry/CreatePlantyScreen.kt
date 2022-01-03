package com.example.planty.ui.createEntry

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.common.composables.PlusFAB
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun CreatePlantyView(
    viewModel: CreatePlantyViewModel,
    onBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    val currBottomNavRoute = remember { mutableStateOf(HomeScreen) }
    val scaffoldState = rememberScaffoldState()

    CreatePlantyView(
        uiState = uiState.value,
        onBack = onBack,
        onFabClicked = {},
        scaffoldState = scaffoldState
    )
}

@Composable
private fun CreatePlantyView(
    uiState: CreatePlantyUiState,
    onBack: () -> Unit,
    onFabClicked: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { CreatePlantyTopBar(onBack) },
        floatingActionButton = { PlusFAB { onFabClicked() } }
    ) {
       CreateEntryCard()
    }
}

@Composable
private fun CreatePlantyTopBar(onBack: () -> Unit) {
    InsetAwareTopAppBar(
        title = { Text(text = stringResource(id = R.string.Create_Entry)) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.Navigate_Back),
                modifier = Modifier
                    .padding(start = Dimen.XL)
                    .clickable { onBack() })
        }
    )
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    val uistate = CreatePlantyUiState()

    PlantyTheme {
        CreatePlantyView(
            uiState = uistate,
            onBack = {},
            onFabClicked = {},
            scaffoldState = rememberScaffoldState()
        )
    }
}
