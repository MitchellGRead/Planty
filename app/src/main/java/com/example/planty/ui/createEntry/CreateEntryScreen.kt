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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.common.composables.PlusFAB
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun CreateEntryView(
    viewModel: OutdoorEntryViewModel,
    onBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    CreateEntryView(
        uiState = uiState.value,
        onLocationUpdated = { location -> viewModel.updateLocation(location) },
        onSeedTypeUpdated = { seedType -> viewModel.updateSeedType(seedType) },
        onPlantCategoryUpdated = { plantCategory -> viewModel.updatePlantCategory(plantCategory) },
        onBack = onBack,
        onFabClicked = { viewModel.createPlantyEntry() },
        scaffoldState = scaffoldState
    )
}

@Composable
private fun CreateEntryView(
    uiState: OutdoorEntryUiState,
    onLocationUpdated: (location: String) -> Unit,
    onSeedTypeUpdated: (seedType: String) -> Unit,
    onPlantCategoryUpdated: (plantCategory: String) -> Unit,
    onBack: () -> Unit,
    onFabClicked: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { CreatePlantyTopBar(onBack) },
        floatingActionButton = {
            PlusFAB {
                onFabClicked()
                onBack()
            }
        }
    ) {
        OutdoorCreateEntryCard(
            uiState,
            onLocationUpdated,
            onSeedTypeUpdated,
            onPlantCategoryUpdated
        )
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
fun DefaultCreateEntryScreenPreview() {
    val uistate = OutdoorEntryUiState()

    PlantyTheme {
        CreateEntryView(
            uiState = uistate,
            onLocationUpdated = {},
            onSeedTypeUpdated = {},
            onPlantCategoryUpdated = {},
            onBack = {},
            onFabClicked = {},
            scaffoldState = rememberScaffoldState()
        )
    }
}
