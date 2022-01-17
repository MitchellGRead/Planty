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
class OutdoorEntryViewModel @Inject constructor(
    private val plantEntryRepo: PlantEntryRepo
) : ViewModel() {
    private val _uiState = MutableStateFlow(OutdoorEntryUiState())
    val uiState: StateFlow<OutdoorEntryUiState> = _uiState.asStateFlow()

    fun updateLocation(location: String) {
        updateUiState(_uiState.value.copy(location = location))
    }

    fun updateSeedType(seedType: String) {
        updateUiState(_uiState.value.copy(seedType = seedType))
    }

    fun updatePlantCategory(plantCategory: String) {
        updateUiState(_uiState.value.copy(plantCategory = plantCategory))
    }

    private fun updateUiState(newState: OutdoorEntryUiState) {
        _uiState.update { newState }
    }

    fun createPlantyEntry() {
        Timber.d("${_uiState.value}")
        viewModelScope.launch {
            plantEntryRepo.createPlantEntry(_uiState.value)
        }
    }
}
