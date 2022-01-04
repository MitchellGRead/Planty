package com.example.planty.ui.createEntry

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Yard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.ui.common.composables.DashedSlider
import com.example.planty.ui.common.composables.OutlinedDropdownMenu
import com.example.planty.ui.theme.AndroidHint
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.Shapes

data class CreateEntryCardUiModel(
    val screenState: CreatePlantyUiState,
    val onStateUpdate: (state: CreatePlantyUiState) -> Unit,
    val sliderValues: List<String>,
    val adoptionDateMenuOptions: List<String>,
    val locationMenuOptions: List<String>,
    val plantTypeMenuOptions: List<String>
)

@Composable
internal fun CreateEntryCard(cardUiModel: CreateEntryCardUiModel) {
    val screenState = cardUiModel.screenState

    val plantName = rememberSaveable{ mutableStateOf(screenState.plantName) }
    val adoptionDate = rememberSaveable{ mutableStateOf(screenState.adoptionDate) }
    val waterReq = rememberSaveable{ mutableStateOf(screenState.waterReq) }
    val lightReq = rememberSaveable{ mutableStateOf(screenState.lightReq) }
    val location = rememberSaveable{ mutableStateOf(screenState.location) }
    val plantType = rememberSaveable{ mutableStateOf(screenState.plantType) }

    val sliderValues = cardUiModel.sliderValues
    val adoptionDateMenuOptions = cardUiModel.adoptionDateMenuOptions
    val locationMenuOptions = cardUiModel.locationMenuOptions
    val plantTypeMenuOptions = cardUiModel.plantTypeMenuOptions

    Card(
        shape = Shapes.large,
        modifier = Modifier
            .padding(Dimen.L)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(Dimen.XL)
                .fillMaxWidth()
        ) {
            TextEntry(
                label = R.string.Name,
                text = plantName,
                leadingIcon = { Icon(imageVector = Icons.Filled.Label, contentDescription = "") },
                onTextChanged = {
                    plantName.value = it
                    cardUiModel.onStateUpdate(cardUiModel.screenState.copy(plantName = it))
                }
            )

            MenuSpacer()

            MenuSlider(
                sliderValues = sliderValues,
                label = R.string.Water,
                leadingIcon = { Icon(
                    painter = painterResource(id = R.drawable.water_drop),
                    contentDescription = "",
                    tint = AndroidHint
                ) },
                onSliderChange = {
                    waterReq.value = sliderValues[it]
                    cardUiModel.onStateUpdate(cardUiModel.screenState.copy(waterReq = waterReq.value))
                }
            )

            MenuSpacer()

            MenuSlider(
                sliderValues = sliderValues,
                label = R.string.Light,
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = "",
                    tint = AndroidHint
                ) },
                onSliderChange = {
                    lightReq.value = sliderValues[it]
                    cardUiModel.onStateUpdate(cardUiModel.screenState.copy(lightReq = lightReq.value))
                }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Adoption_Date,
                menuOptions = adoptionDateMenuOptions,
                onOptionSelected = {
                    adoptionDate.value = it
                    cardUiModel.onStateUpdate(cardUiModel.screenState.copy(adoptionDate = it))
                },
                leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = "") }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Location,
                menuOptions = locationMenuOptions,
                onOptionSelected = {
                    location.value = it
                    cardUiModel.onStateUpdate(cardUiModel.screenState.copy(location = it))
                },
                leadingIcon = { Icon(imageVector = Icons.Filled.Room, contentDescription = "") }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Plant_Type,
                menuOptions = plantTypeMenuOptions,
                onOptionSelected = {
                    plantType.value = it
                    cardUiModel.onStateUpdate(cardUiModel.screenState.copy(plantType = it))
                },
                leadingIcon = { Icon(imageVector = Icons.Filled.Yard, contentDescription = "") }
            )
        }
    }
}

@Composable
private fun TextEntry(
    @StringRes label: Int,
    text: MutableState<String>,
    leadingIcon: @Composable (() -> Unit)? = null,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { onTextChanged(it) },
        label = { Text(stringResource(id = label)) },
        leadingIcon = leadingIcon,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MenuSlider(
    @StringRes label: Int,
    sliderValues: List<String>,
    leadingIcon: @Composable (() -> Unit)? = null,
    onSliderChange: (Int) -> Unit
) {
    Column {
        Row(modifier = Modifier
            .padding(start = Dimen.XL)
            .offset(y = Dimen.L)
        ) {
            leadingIcon?.let {
                leadingIcon()
            }
            Text(
                text = stringResource(id = label),
                color = AndroidHint,
                modifier = Modifier.padding(start = Dimen.XXL)
            )
        }
        DashedSlider(
            sliderValues = sliderValues,
            value = (sliderValues.size / 2).toFloat(),
            onValueChange = { onSliderChange(it) }
        )
    }
}

@Composable
private fun MenuSpacer() {
    Spacer(modifier = Modifier.padding(Dimen.XL))
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultCreateEntryCardPreview() {
    val cardUiModel = CreateEntryCardUiModel(
        screenState = CreatePlantyUiState(),
        onStateUpdate = {},
        sliderValues = listOf("very little", "little", "moderate", "lots", "tons"),
        adoptionDateMenuOptions = listOf(),
        locationMenuOptions = listOf(),
        plantTypeMenuOptions = listOf()
    )
    CreateEntryCard(cardUiModel = cardUiModel)
}
