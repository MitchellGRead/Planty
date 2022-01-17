package com.example.planty.ui.createEntry

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.filled.Yard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.planty.R
import com.example.planty.ui.common.composables.OutlinedDropdownMenu
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.Shapes

@Composable
internal fun OutdoorCreateEntryCard(
    uiState: OutdoorEntryUiState,
    onLocationUpdated: (location: String) -> Unit,
    onSeedTypeUpdated: (seedType: String) -> Unit,
    onPlantCategoryUpdated: (plantCategory: String) -> Unit,
) {

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
                label = R.string.Location,
                text = uiState.location,
                leadingIcon = { Icon(imageVector = Icons.Filled.Room, contentDescription = "") },
                onTextChanged = { onLocationUpdated(it) }
            )

            MenuSpacer()

            TextEntry(
                label = R.string.Seed_Type,
                text = uiState.seedType,
                leadingIcon = { Icon(imageVector = Icons.Filled.Yard, contentDescription = "") },
                onTextChanged = { onSeedTypeUpdated(it) }
            )

            MenuSpacer()

            TextEntry(
                label = R.string.Plant_Category,
                text = uiState.plantCategory,
                leadingIcon = { Icon(imageVector = Icons.Filled.List, contentDescription = "") },
                onTextChanged = { onPlantCategoryUpdated(it) }
            )

            OutlinedDropdownMenu(
                label = R.string.Plant_Category,
                menuOptions = listOf("Test","Test","Test","Test","Test",),
                onOptionChanged = {}
            )

//            MenuSlider(
//                sliderValues = uiState.sliderValues,
//                sliderStartPosition = uiState.startingSliderPosition,
//                label = R.string.Water,
//                leadingIcon = { Icon(
//                    painter = painterResource(id = R.drawable.water_drop),
//                    contentDescription = "",
//                    tint = AndroidHint
//                ) },
//                onSliderChange = { onSliderUpdated(WaterSliderTag, it) }
//            )
//
//            MenuSpacer()
//
//            MenuSlider(
//                sliderValues = uiState.sliderValues,
//                sliderStartPosition = uiState.startingSliderPosition,
//                label = R.string.Light,
//                leadingIcon = { Icon(
//                    imageVector = Icons.Filled.WbSunny,
//                    contentDescription = "",
//                    tint = AndroidHint
//                ) },
//                onSliderChange = { onSliderUpdated(LightSliderTag, it) }
//            )
//
//            MenuSpacer()
//
//            OutlinedDropdownMenu(
//                label = R.string.Adoption_Date,
//                menuOptions = uiState.adoptionMenuOptions,
//                onOptionChanged = { onDropdownMenuUpdated(AdoptionTag, it) },
//                leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = "") }
//            )
//
//            MenuSpacer()
//
//            OutlinedDropdownMenu(
//                label = R.string.Location,
//                menuOptions = uiState.locationMenuOptions,
//                onOptionChanged = { onDropdownMenuUpdated(LocationTag, it) },
//                leadingIcon = { Icon(imageVector = Icons.Filled.Room, contentDescription = "") }
//            )
//
//            MenuSpacer()
//
//            OutlinedDropdownMenu(
//                label = R.string.Plant_Type,
//                menuOptions = uiState.plantTypeMenuOptions,
//                onOptionChanged = { onDropdownMenuUpdated(PlantTypeTag, it) },
//                leadingIcon = { Icon(imageVector = Icons.Filled.Yard, contentDescription = "") }
//            )
        }
    }
}

@Composable
private fun TextEntry(
    @StringRes label: Int,
    text: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        label = { Text(stringResource(id = label)) },
        leadingIcon = leadingIcon,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MenuSpacer() {
    Spacer(modifier = Modifier.padding(Dimen.XL))
}

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultCreateEntryCardPreview() {
    OutdoorCreateEntryCard(
        uiState = OutdoorEntryUiState(),
        onLocationUpdated = {},
        onSeedTypeUpdated = {},
        onPlantCategoryUpdated = {},
    )
}
