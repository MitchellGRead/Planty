package com.example.planty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.domain.model.PlantEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState(loading = true))
    val uiState = _uiState.asStateFlow()

    fun onStart() {
        updatePlantEntries()
    }

    private fun updatePlantEntries() {
        _uiState.update { HomeUiState(loading = true) }

        val card = PlantEntry(id = "1", name = "Planty")
        val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)

        viewModelScope.launch {
            _uiState.update {
                HomeUiState(
                    loading = false,
                    plantEntries = cards
                )
            }
        }
    }
}
