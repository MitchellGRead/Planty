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

    fun updateName(newName: String) {
        updateUiState(_uiState.value.copy(plantName = newName))
    }

    fun updateSliderValue(tag: SliderTag, sliderPos: Int) {
        with(_uiState.value) {
            val sliderValue = sliderValues[sliderPos]
            when (tag) {
                LightSlider -> updateUiState(copy(lightReq = sliderValue))
                WaterSlider -> updateUiState(copy(waterReq = sliderValue))
            }
        }
    }

    fun updateDropdownMenu(tag: DropdownTag, newValue: String) {
        when (tag) {
            AdoptionTag -> updateAdoption(newValue)
            LocationTag -> updateLocation(newValue)
            PlantTypeTag -> updatePlantType(newValue)
        }
    }

    private fun updateUiState(newState: CreatePlantyUiState) {
        _uiState.update { newState }
    }

    private fun updateAdoption(adoption: String) {

    }

    private fun updateLocation(location: String) {

    }

    private fun updatePlantType(plantType: String) {

    }

    fun createPlantyEntry() {
        Timber.d("${_uiState.value}")
        viewModelScope.launch {
            plantEntryRepo.createPlantEntry(_uiState.value)
        }
    }
}
