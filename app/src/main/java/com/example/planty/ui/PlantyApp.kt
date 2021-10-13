package com.example.planty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.planty.ui.navigation.PlantyNavGraph
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun PlantyApp() {
    PlantyTheme {
        val navController = rememberNavController()
        PlantyNavGraph(
            navController = navController
        )
    }
}
