package com.example.planty.ui.createEntry

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.ui.common.composables.InsetAwareTopAppBar
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun CreatePlantyView(viewModel: CreatePlantyViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    val currBottomNavRoute = remember { mutableStateOf(HomeScreen) }
    val scaffoldState = rememberScaffoldState()

    CreatePlantyView(
        uiState = uiState.value,
        navigateUp = {},
        scaffoldState = scaffoldState
    )
}

@Composable
private fun CreatePlantyView(
    uiState: CreatePlantyUiState,
    navigateUp: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { CreatePlantyTopBar(navigateUp) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            CreateEntryCard()
        }
    }
}

@Composable
private fun CreatePlantyTopBar(navigateUp: () -> Unit) {
    InsetAwareTopAppBar(
        title = { Text(text = stringResource(id = R.string.Create_Entry)) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.Navigate_Back),
                modifier = Modifier
                    .padding(start = Dimen.XL)
                    .clickable { navigateUp() })
        }
    )
}

@Composable
private fun CreateEntryCard() {
    val text = rememberSaveable{ mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(Dimen.XL)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    val uistate = CreatePlantyUiState(id = "1")

    PlantyTheme {
        CreatePlantyView(
            uiState = uistate,
            navigateUp = {},
            scaffoldState = rememberScaffoldState()
        )
    }
}
