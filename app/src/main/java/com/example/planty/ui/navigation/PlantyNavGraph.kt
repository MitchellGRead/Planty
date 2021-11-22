package com.example.planty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planty.ui.home.HomeScreenView
import com.example.planty.ui.home.HomeViewModel

@Composable
fun PlantyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreenRoute.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(HomeScreenRoute.route) {
            val viewModel: HomeViewModel = hiltViewModel(it)
            HomeScreenView(viewModel = viewModel)
        }
    }
}
