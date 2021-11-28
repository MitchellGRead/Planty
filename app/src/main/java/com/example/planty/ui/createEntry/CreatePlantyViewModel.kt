package com.example.planty.ui.createEntry

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreatePlantyViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CreatePlantyUiState(id = "stuff"))
    val uiState: StateFlow<CreatePlantyUiState> = _uiState.asStateFlow()
}
