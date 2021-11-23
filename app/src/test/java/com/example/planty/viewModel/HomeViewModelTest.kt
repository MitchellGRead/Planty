package com.example.planty.viewModel

import com.example.planty.MainCoroutineRule
import com.example.planty.data.PlantEntryFactory
import com.example.planty.domain.PlantEntryRepo
import com.example.planty.ui.home.HomeUiState
import com.example.planty.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val mockPlantEntryRepo: PlantEntryRepo = mock()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            plantEntryRepo = mockPlantEntryRepo
        )
    }

    @Test
    fun `WHEN viewModel initialized THEN starts in loading state`() {
        val expected = HomeUiState(loading = true)

        val actual = viewModel.uiState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN onStart THEN uiState plantEntries updated`() {
        val entries = listOf(PlantEntryFactory.basicPlantEntry)
        whenever(mockPlantEntryRepo.fetchPlantEntries()).thenReturn(entries)
        val uiState = HomeUiState(plantEntries = entries)

        viewModel.onStart()

        assertEquals(uiState, viewModel.uiState.value)
    }

    @Test
    fun `WHEN updatePlantEntries THEN uiState goes from loading to loaded`() = coroutineRule.runBlockingTest {
        val firstState = HomeUiState(loading = true)
        val secondState = HomeUiState(loading = false)
        val actuals = mutableListOf<HomeUiState>()
        val job = launch {
            viewModel.uiState.toList(actuals)
        }

        viewModel.updatePlantEntries()

        assertEquals(firstState, actuals[0])
        assertEquals(secondState, actuals[1])
        job.cancel()
    }

    @Test
    fun `WHEN updatePlantEntries THEN fetch plant entry results`() {
        viewModel.updatePlantEntries()

        verify(mockPlantEntryRepo).fetchPlantEntries()
    }

    @Test
    fun `WHEN createPlantEntry THEN uiState updated with new entry`() = coroutineRule.runBlockingTest {
        val entries = listOf(PlantEntryFactory.basicPlantEntry)
        whenever(mockPlantEntryRepo.fetchPlantEntries()).thenReturn(entries)
        val uiState = HomeUiState(plantEntries = entries)

        viewModel.createPlantEntry()

        assertEquals(uiState, viewModel.uiState.value)
        verify(mockPlantEntryRepo).createPlantEntry()
        verify(mockPlantEntryRepo).fetchPlantEntries()
    }

}
