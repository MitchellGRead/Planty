package com.example.planty.viewModel

import com.example.planty.MainCoroutineRule
import com.example.planty.domain.PlantEntryRepo
import com.example.planty.ui.createEntry.OutdoorEntryViewModel
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
class OutdoorEntryViewModelTest {
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
    fun `GIVEN new location WHEN updateLocation THEN state is updated with new location`() {
        val expected = "Tom"

        viewModel.updateLocation(expected)

        assertEquals(expected, viewModel.uiState.value.location)
    }

    @Test
    fun `GIVEN new seed type WHEN updateSeedType THEN state is updated with new seed type`() {
        val expected = "banana"

        viewModel.updateSeedType(expected)

        assertEquals(expected, viewModel.uiState.value.seedType)
    }

    @Test
    fun `GIVEN new plant category WHEN updatePlantCategory THEN state is updated with new plant category`() {
        val expected = "squash"

        viewModel.updatePlantCategory(expected)

        assertEquals(expected, viewModel.uiState.value.plantCategory)
    }

    @Test
    fun `GIVEN uiState WHEN createPlantyEntry THEN new card is created`() {
        viewModel.createPlantyEntry()

        coroutineRule.runBlockingTest {
            verify(mockPlantEntryRepo).createPlantEntry(any())
        }
    }
}
