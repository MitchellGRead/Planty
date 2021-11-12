package com.example.planty.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val dest: PlantyRoute, val label: String? = null, val icon: ImageVector? = null)

object HomeScreen : Screen(HomeScreenRoute, "Home", Icons.Filled.Home)
object ScheduleScreen : Screen(ScheduleScreenRoute, "Schedule", Icons.Filled.DateRange)
object CreatePlantEntryScreen : Screen(CameraRoute, "Create", null)
