package com.example.planty.ui.entryInfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class EntryInfoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EntryInfoUiState())
    val uiState: StateFlow<EntryInfoUiState>
        get() = _uiState.asStateFlow()
}
