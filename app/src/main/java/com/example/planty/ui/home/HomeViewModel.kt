package com.example.planty.ui.home

import androidx.lifecycle.ViewModel
import com.example.planty.domain.model.PlantEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<List<PlantEntry>>(listOf())
    val uiState = _uiState.asStateFlow()

    init {
        val card = PlantEntry(id = "1", name = "Planty")
        val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)
        _uiState.value = cards
    }
}
