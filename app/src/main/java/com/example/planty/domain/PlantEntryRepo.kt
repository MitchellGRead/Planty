package com.example.planty.domain

import com.example.planty.domain.model.PlantEntry
import com.example.planty.hilt.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PlantEntryRepo(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    private var cards: List<PlantEntry> = listOf()
    private val card = PlantEntry(id = "1", name = "Planty")

    suspend fun createPlantEntry() {
        withContext(ioDispatcher) {

            cards = cards + listOf(card)
        }
    }

    fun fetchPlantEntries(): List<PlantEntry> = cards
}
