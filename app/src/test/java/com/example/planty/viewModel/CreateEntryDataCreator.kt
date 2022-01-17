package com.example.planty.viewModel

import com.example.planty.ui.createEntry.CreatePlantyUiState
import com.example.planty.ui.createEntry.SliderValues

const val PLANT_NAME = "Jerry"
val WATER_REQ = SliderValues.MODERATE
val LIGHT_REQ = SliderValues.MODERATE
const val ADOPTION_DATE = "tomorrow"
const val LOCATION = "bathroom"
const val PLANT_TYPE = "cactus"

fun createPlantyUiState(
    plantName: String = PLANT_NAME,
    waterReq: SliderValues = WATER_REQ,
    lightReq: SliderValues = LIGHT_REQ,
    adoptionDate: String = ADOPTION_DATE,
    location: String = LOCATION,
    plantType: String = PLANT_TYPE
) = CreatePlantyUiState(
    plantName = plantName,
    waterReq = waterReq,
    lightReq = lightReq,
    adoptionDate = adoptionDate,
    location = location,
    plantType = plantType
)
