package com.example.planty.viewModel

import com.example.planty.ui.createEntry.CreatePlantyUiState

fun createPlantyUiState(
    plantName: String = "Jerry",
    waterReq: String = "some",
    lightReq: String = "some",
    adoptionDate: String = "tomorrow",
    location: String = "bathroom",
    plantType: String = "cactus"
) = CreatePlantyUiState(
    plantName = plantName,
    waterReq = waterReq,
    lightReq = lightReq,
    adoptionDate = adoptionDate,
    location = location,
    plantType = plantType
)
