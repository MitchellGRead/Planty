package com.example.planty.viewModel

import com.example.planty.MainCoroutineRule
import com.example.planty.domain.PlantEntryRepo
import com.example.planty.ui.createEntry.CreatePlantyViewModel
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

    private lateinit var viewModel: CreatePlantyViewModel

    @Before
    fun setUp() {
        viewModel = CreatePlantyViewModel(
            plantEntryRepo = mockPlantEntryRepo
        )
    }

    @Test
    fun `GIVEN uiState WHEN updateUiState THEN state is updated to new state`() {
        val newState = createPlantyUiState()

        viewModel.updateUiState(newState = newState)

        assertEquals(newState, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN uiState WHEN createPlantyEntry THEN new card is created`() {
        val createCard = createPlantyUiState()
        viewModel.updateUiState(createCard)

        viewModel.createPlantyEntry()

        coroutineRule.runBlockingTest {
            verify(mockPlantEntryRepo).createPlantEntry(createCard)
        }
    }
}
