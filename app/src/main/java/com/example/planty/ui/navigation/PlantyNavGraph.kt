package com.example.planty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planty.ui.home.HomeScreen

@Composable
fun PlantyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = PlantyDestinations.HOME_SCREEN_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(PlantyDestinations.HOME_SCREEN_ROUTE) {
            HomeScreen(name = "Mitchell")
        }
    }
}
