package com.example.planty.ui.common.composables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.planty.R

@Composable
fun PlusFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.PlusFABContentDescription)
        )
    }
}
