package com.example.planty.domain

import com.example.planty.domain.model.PlantEntry
import com.example.planty.hilt.AppIoScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PlantEntryRepo(
    @AppIoScope private val appIoScope: CoroutineScope,
) {
    private var cards: List<PlantEntry> = listOf()
    private val card = PlantEntry(id = "1", name = "Planty")

    fun createPlantEntry() {
        appIoScope.launch {
            cards = cards + listOf(card)
        }
    }

    fun fetchPlantEntries(): List<PlantEntry> = cards
}
