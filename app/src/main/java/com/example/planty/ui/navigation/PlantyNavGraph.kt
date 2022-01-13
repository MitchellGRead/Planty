package com.example.planty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planty.ui.createEntry.CreatePlantyView
import com.example.planty.ui.createEntry.CreatePlantyViewModel
import com.example.planty.ui.entryInfo.EntryInfoView
import com.example.planty.ui.entryInfo.EntryInfoViewModel
import com.example.planty.ui.home.HomeScreenView
import com.example.planty.ui.home.HomeViewModel

@Composable
fun PlantyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreen.route
) {
    val actions = remember(navController) { NavActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel(it)
            HomeScreenView(
                viewModel = viewModel,
                onFabClicked = actions.navigateToCreateEntry,
                onPlantCardClicked = actions.navigateToEntryInfo
            )
        }

        composable(CreateEntryScreen.route) {
            val viewModel: CreatePlantyViewModel = hiltViewModel(it)
            CreatePlantyView(
                viewModel = viewModel,
                onBack = actions.navigateUp
            )
        }

        composable(EntryInfoScreen.route) {
            val viewModel: EntryInfoViewModel = hiltViewModel(it)
            EntryInfoView(
                viewModel = viewModel,
                onBack = actions.navigateUp
            )
        }
    }
}


