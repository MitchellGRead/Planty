package com.example.planty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.domain.PlantEntryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val plantEntryRepo: PlantEntryRepo
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(loading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onStart() {
        updatePlantEntries()
    }

    fun updatePlantEntries() {
        _uiState.update { HomeUiState(loading = true) }

        viewModelScope.launch {
            _uiState.update {
                HomeUiState(
                    loading = false,
                    plantEntries = plantEntryRepo.fetchPlantEntries()
                )
            }
        }
    }

    fun createPlantEntry() {
        viewModelScope.launch {
            plantEntryRepo.createPlantEntry()
            updatePlantEntries()
        }
    }
}
