package com.example.planty.viewModel

import com.example.planty.MainCoroutineRule
import com.example.planty.domain.PlantEntryRepo
import com.example.planty.ui.createEntry.AdoptionTag
import com.example.planty.ui.createEntry.OutdoorEntryViewModel
import com.example.planty.ui.createEntry.LightSliderTag
import com.example.planty.ui.createEntry.LocationTag
import com.example.planty.ui.createEntry.PlantTypeTag
import com.example.planty.ui.createEntry.SliderValues
import com.example.planty.ui.createEntry.WaterSliderTag
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CreatePlantyViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val mockPlantEntryRepo: PlantEntryRepo = mock()

    private lateinit var viewModel: OutdoorEntryViewModel

    @Before
    fun setUp() {
        viewModel = OutdoorEntryViewModel(
            plantEntryRepo = mockPlantEntryRepo
        )
    }

    @Test
    fun `GIVEN new plant name WHEN updateName THEN state is updated with new name`() {
        val expected = "Tom"

        viewModel.updateName(expected)

        assertEquals(expected, viewModel.uiState.value.plantName)
    }

    @Test
    fun `GIVEN new light req level WHEN updateSliderValue THEN state is updated with new light req level`() {
        val uiState = createPlantyUiState()
        val expected = SliderValues.TONS
        val newSliderPos = uiState.sliderValues.indexOf(expected)

        viewModel.updateSliderValue(LightSliderTag, newSliderPos)

        assertEquals(expected, viewModel.uiState.value.lightReq)
    }

    @Test
    fun `GIVEN new water req level WHEN updateSliderValue THEN state is updated with new water req level`() {
        val uiState = createPlantyUiState()
        val expected = SliderValues.TONS
        val newSliderPos = uiState.sliderValues.indexOf(expected)

        viewModel.updateSliderValue(WaterSliderTag, newSliderPos)

        assertEquals(expected, viewModel.uiState.value.waterReq)
    }

    @Test
    fun `GIVEN new adoption day WHEN updateDropdownMenu THEN state is updated with new adoption day`() {
        val expected = "three days ago"

        viewModel.updateDropdownMenu(AdoptionTag, expected)

        assertEquals(expected, viewModel.uiState.value.adoptionDate)
    }

    @Test
    fun `GIVEN new location WHEN updateDropdownMenu THEN state is updated with new location`() {
        val expected = "sink"

        viewModel.updateDropdownMenu(LocationTag, expected)

        assertEquals(expected, viewModel.uiState.value.location)
    }

    @Test
    fun `GIVEN new plant type WHEN updateDropdownMenu THEN state is updated with new plant type`() {
        val expected = "fungus"

        viewModel.updateDropdownMenu(PlantTypeTag, expected)

        assertEquals(expected, viewModel.uiState.value.plantType)
    }

    @Test
    fun `GIVEN uiState WHEN createPlantyEntry THEN new card is created`() {
        viewModel.createPlantyEntry()

        coroutineRule.runBlockingTest {
            verify(mockPlantEntryRepo).createPlantEntry(any())
        }
    }
}
