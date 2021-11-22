package com.example.planty.ui.home

import com.example.planty.domain.model.PlantEntry
import com.example.planty.shared.ErrorMessage

data class HomeUiState(
    val loading: Boolean = false,
    val plantEntries: List<PlantEntry> = listOf(),
    val error: List<ErrorMessage> = listOf()
)
