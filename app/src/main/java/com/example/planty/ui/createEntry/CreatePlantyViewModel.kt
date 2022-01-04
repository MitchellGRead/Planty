package com.example.planty.ui.createEntry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.domain.PlantEntryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreatePlantyViewModel @Inject constructor(
    private val plantEntryRepo: PlantEntryRepo
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreatePlantyUiState())
    val uiState: StateFlow<CreatePlantyUiState> = _uiState.asStateFlow()

    val sliderValues: List<String>
        get() = listOf("very little", "little", "moderate", "lots", "tons")
    val adoptionDateMenuOptions: List<String>
        get() = listOf("today", "yesterday", "tomorrow")
    val locationMenuOptions: List<String>
        get() = listOf("Living Room", "Bedroom", "Kitchen", "Bathroom")
    val plantTypeMenuOptions: List<String>
        get() = listOf("Cactus", "Flower", "Thing")

    fun updateUiState(newState: CreatePlantyUiState) {
        _uiState.update { newState }
    }

    fun createPlantyEntry() {
        Timber.d("${uiState.value}")
        viewModelScope.launch {
            plantEntryRepo.createPlantEntry(uiState.value)
        }
    }
}
