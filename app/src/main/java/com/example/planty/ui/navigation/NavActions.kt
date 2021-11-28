package com.example.planty.ui.navigation

import androidx.navigation.NavController

class NavActions(navController: NavController) {
    val navigateToCreateEntry: () -> Unit = {
        navController.navigate(CreateEntryScreen.route)
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}
