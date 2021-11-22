package com.example.planty

import com.example.planty.domain.model.PlantEntry
import com.example.planty.ui.home.HomeUiState
import com.example.planty.ui.home.HomeViewModel
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

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    @Test
    fun `WHEN viewModel initialized THEN starts in loading state`() {
        val expected = HomeUiState(loading = true)

        val actual = viewModel.uiState.value

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN updatePlantEntries THEN state goes from loading to loaded with plant entries`() = coroutineRule.runBlockingTest {
        val card = PlantEntry(id = "1", name = "Planty")
        val cards: List<PlantEntry> = listOf(card, card, card, card,card, card, card, card,card, card, card, card)
        val firstState = HomeUiState(loading = true)
        val secondState = HomeUiState(plantEntries = cards)
        val actuals = mutableListOf<HomeUiState>()
        val job = launch {
            viewModel.uiState.toList(actuals)
        }

        viewModel.updatePlantEntries()

        assertEquals(firstState, actuals[0])
        assertEquals(secondState, actuals[1])
        job.cancel()
    }

}
