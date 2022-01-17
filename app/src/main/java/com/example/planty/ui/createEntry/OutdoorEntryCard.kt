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

sealed class SliderTag
object WaterSliderTag : SliderTag()
object LightSliderTag : SliderTag()

sealed class DropdownTag
object AdoptionTag : DropdownTag()
object LocationTag : DropdownTag()
object PlantTypeTag : DropdownTag()

@Composable
internal fun CreateEntryCard(
    uiState: CreatePlantyUiState,
    onNameUpdated: (name: String) -> Unit,
    onSliderUpdated: (tag: SliderTag, value: Int) -> Unit,
    onDropdownMenuUpdated: (tag: DropdownTag, value: String) -> Unit,
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
                label = R.string.Name,
                text = uiState.plantName,
                leadingIcon = { Icon(imageVector = Icons.Filled.Label, contentDescription = "") },
                onTextChanged = { onNameUpdated(it) }
            )

            MenuSpacer()

            MenuSlider(
                sliderValues = uiState.sliderValues,
                sliderStartPosition = uiState.startingSliderPosition,
                label = R.string.Water,
                leadingIcon = { Icon(
                    painter = painterResource(id = R.drawable.water_drop),
                    contentDescription = "",
                    tint = AndroidHint
                ) },
                onSliderChange = { onSliderUpdated(WaterSliderTag, it) }
            )

            MenuSpacer()

            MenuSlider(
                sliderValues = uiState.sliderValues,
                sliderStartPosition = uiState.startingSliderPosition,
                label = R.string.Light,
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = "",
                    tint = AndroidHint
                ) },
                onSliderChange = { onSliderUpdated(LightSliderTag, it) }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Adoption_Date,
                menuOptions = uiState.adoptionMenuOptions,
                onOptionChanged = { onDropdownMenuUpdated(AdoptionTag, it) },
                leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = "") }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Location,
                menuOptions = uiState.locationMenuOptions,
                onOptionChanged = { onDropdownMenuUpdated(LocationTag, it) },
                leadingIcon = { Icon(imageVector = Icons.Filled.Room, contentDescription = "") }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Plant_Type,
                menuOptions = uiState.plantTypeMenuOptions,
                onOptionChanged = { onDropdownMenuUpdated(PlantTypeTag, it) },
                leadingIcon = { Icon(imageVector = Icons.Filled.Yard, contentDescription = "") }
            )
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
private fun MenuSlider(
    @StringRes label: Int,
    sliderValues: List<SliderValues>,
    sliderStartPosition: Float,
    leadingIcon: @Composable (() -> Unit)? = null,
    onSliderChange: (Int) -> Unit
) {
    Column {
        Row(modifier = Modifier
            .padding(start = Dimen.XL)
            .offset(y = Dimen.L)
        ) {
            leadingIcon?.let { leadingIcon() }
            Text(
                text = stringResource(id = label),
                color = AndroidHint,
                modifier = Modifier.padding(start = Dimen.XXL)
            )
        }
        DashedSlider(
            sliderValues = sliderValues.map { it.value },
            value = sliderStartPosition,
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
    CreateEntryCard(
        uiState = CreatePlantyUiState(),
        onNameUpdated = {},
        onSliderUpdated = {_, _ -> },
        onDropdownMenuUpdated = {_, _ -> }
    )
}
