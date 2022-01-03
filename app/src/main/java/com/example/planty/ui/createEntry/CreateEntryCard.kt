package com.example.planty.ui.createEntry

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
import com.example.planty.R
import com.example.planty.ui.common.composables.DashedSlider
import com.example.planty.ui.common.composables.OutlinedDropdownMenu
import com.example.planty.ui.theme.AndroidHint
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.Shapes

@Composable
internal fun CreateEntryCard() {
    val text = rememberSaveable{ mutableStateOf("") }
    val sliderValues = listOf("less", "less more", "more", "more less", "most")
    val options = listOf("A", "B", "C", "D")

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
                text = text,
                leadingIcon = { Icon(imageVector = Icons.Filled.Label, contentDescription = "") }
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
                onSliderChange = {}
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
                onSliderChange = {}
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Adoption_Date,
                menuOptions = options,
                onOptionSelected = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = "") }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Location,
                menuOptions = options,
                onOptionSelected = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Room, contentDescription = "") }
            )

            MenuSpacer()

            OutlinedDropdownMenu(
                label = R.string.Plant_Type,
                menuOptions = options,
                onOptionSelected = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Yard, contentDescription = "") }
            )

            MenuSpacer()
        }
    }
}

@Composable
private fun TextEntry(
    @StringRes label: Int,
    text: MutableState<String>,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
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
    onSliderChange: () -> Unit
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
            onValueChange = { onSliderChange() }
        )
    }
}

@Composable
private fun MenuSpacer() {
    Spacer(modifier = Modifier.padding(Dimen.XL))
}
