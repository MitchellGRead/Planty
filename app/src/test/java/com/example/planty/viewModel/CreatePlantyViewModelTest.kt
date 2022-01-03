package com.example.planty.viewModel

import com.example.planty.MainCoroutineRule
import com.example.planty.domain.PlantEntryRepo
import com.example.planty.ui.createEntry.CreatePlantyViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

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
}
