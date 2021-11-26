package com.example.planty.ui.common.composables

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.planty.ui.theme.Dimen

@Composable
fun PlantyTopBar(
    @StringRes title: Int
) {
    InsetAwareTopAppBar(
        title = { Text(stringResource(id = title)) },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = Dimen.Zero
    )
}
