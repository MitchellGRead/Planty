package com.example.planty.data

import com.example.planty.domain.model.PlantEntry

object PlantEntryFactory {

    val basicPlantEntry: PlantEntry
        get() = PlantEntry(
            id = "1",
            name = "Planty",
            imageUrl = null
        )
}
