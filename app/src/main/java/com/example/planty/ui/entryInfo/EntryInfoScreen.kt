package com.example.planty.ui.entryInfo

import android.content.res.Configuration
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun EntryInfoView(
    viewModel: EntryInfoViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    EntryInfoView(
        uiState = uiState.value,
        scaffoldState = scaffoldState
    )
}

@Composable
private fun EntryInfoView(
    uiState: EntryInfoUiState,
    scaffoldState: ScaffoldState
) {
    Text(text = "Hello")
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultCreatePlantScreenPreview() {
    val uiState = EntryInfoUiState()

    PlantyTheme {
        EntryInfoView(
            uiState = uiState,
            scaffoldState = rememberScaffoldState()
        )
    }
}
