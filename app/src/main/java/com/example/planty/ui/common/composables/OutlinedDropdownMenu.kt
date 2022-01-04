package com.example.planty.ui.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize

@Composable
fun OutlinedDropdownMenu(
    @StringRes label: Int,
    menuOptions: List<String>,
    onOptionChanged: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    val (selectedOption, setSelectedOption) = remember { mutableStateOf("") }
    val (dropDownSize, setDropDownSize) = remember { mutableStateOf(Size.Zero) }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val icon = if (expanded) {
        Icons.Filled.ArrowDropUp
    } else {
        Icons.Filled.ArrowDropDown
    }
    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {
                setSelectedOption(it)
                onOptionChanged(it)
            },
            label = { Text(text = stringResource(id = label)) },
            leadingIcon = leadingIcon,
            trailingIcon = { Icon(
                imageVector = icon,
                contentDescription = ""
            )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                // So we can assign the same width to the dropdown menu
                .onGloballyPositioned { coords -> setDropDownSize(coords.size.toSize()) }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                setExpanded(false)
                focusRequester.freeFocus()
            },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownSize.width.toDp() })
        ) {
            menuOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        setSelectedOption(option)
                        onOptionChanged(option)
                        setExpanded(false)
                    }
                ) {
                    Text(text = option)
                }
            }
        }

        // Expand the clickable area to entire text field
        Canvas(modifier = Modifier
            .width(with(LocalDensity.current) { dropDownSize.width.toDp() })
            .height(with(LocalDensity.current) { dropDownSize.height.toDp() })
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                setExpanded(true)
                focusRequester.requestFocus()
            }
        ) {
            drawRect(
                color = Color.Transparent,
                size = size
            )
        }
    }
}
