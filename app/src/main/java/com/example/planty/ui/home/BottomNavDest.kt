package com.example.planty.ui.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.planty.R
import com.example.planty.ui.navigation.HomeScreen
import com.example.planty.ui.navigation.PlantyScreen
import com.example.planty.ui.navigation.ScheduleScreen

sealed class HomeBottomNavDest(
    val dest: PlantyScreen,
    @StringRes val label: Int,
    val icon: ImageVector
)

object HomeDest : HomeBottomNavDest(
    dest = HomeScreen,
    label = R.string.Home,
    icon = Icons.Filled.Home
)
object ScheduleDest : HomeBottomNavDest(
    dest = ScheduleScreen,
    label = R.string.Schedule,
    icon = Icons.Filled.DateRange
)
