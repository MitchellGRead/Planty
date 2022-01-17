package com.example.planty.viewModel

import com.example.planty.ui.createEntry.OutdoorEntryUiState

const val LOCATION = "bathroom"
const val CATEGORY = "cucumber"
const val SEED_TYPE = "strawberry"

fun createOutdoorEntryUiState(
    location: String = LOCATION,
    seedType: String = SEED_TYPE,
    plantCategory: String = CATEGORY
) = OutdoorEntryUiState(
    location = location,
    seedType = seedType,
    plantCategory = plantCategory
)
