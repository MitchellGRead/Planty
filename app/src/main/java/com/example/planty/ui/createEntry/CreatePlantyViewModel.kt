package com.example.planty.ui.createEntry

import androidx.lifecycle.ViewModel
import com.example.planty.domain.PlantEntryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreatePlantyViewModel @Inject constructor(
    private val plantEntryRepo: PlantEntryRepo
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreatePlantyUiState())
    val uiState: StateFlow<CreatePlantyUiState> = _uiState.asStateFlow()

    val waterLevelOptions = listOf("very little", "little", "moderate", "lots", "tons")
    val lightLevelOptions = listOf("very little", "little", "moderate", "lots", "tons")

    
}
