package com.example.planty.domain

import com.example.planty.domain.model.PlantEntry
import com.example.planty.hilt.IoDispatcher
import com.example.planty.ui.createEntry.CreatePlantyUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PlantEntryRepo(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    private var cards: List<PlantEntry> = listOf()

    suspend fun createPlantEntry(plantData: CreatePlantyUiState) {
        withContext(ioDispatcher) {
            val newEntry = PlantEntry(
                id = "1",
                name = plantData.plantName
            )
            cards = cards + listOf(newEntry)
        }
    }

    fun fetchPlantEntries(): List<PlantEntry> = cards
}
