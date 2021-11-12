package com.example.planty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planty.ui.home.HomeScreenView

@Composable
fun PlantyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreenRoute.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(HomeScreenRoute.route) {
            HomeScreenView()
        }
    }
}
