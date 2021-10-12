package com.example.planty.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PlantyNavGraph(
    navController: NavController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    startDestination: String = PlantyDestinations.HOME_SCREEN_ROUTE
) {
//    TODO - setup nav graph
}
