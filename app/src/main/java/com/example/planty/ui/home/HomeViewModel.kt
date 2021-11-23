package com.example.planty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.domain.model.PlantEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(loading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val card = PlantEntry(id = "1", name = "Planty")

    fun onStart() {
        updatePlantEntries()
    }

    fun updatePlantEntries() {
        _uiState.update { HomeUiState(loading = true) }

        viewModelScope.launch {
            _uiState.update {
                HomeUiState(
                    loading = false,
                    plantEntries = listOf()
                )
            }
        }
    }

    fun createPlantEntry() {
        viewModelScope.launch {
            _uiState.update {
                HomeUiState(plantEntries = listOf(card))
            }
        }
    }
}
