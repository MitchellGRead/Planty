package com.example.planty.ui.navigation

sealed class PlantyScreen(val route: String)

object HomeScreen : PlantyScreen("home")
object ScheduleScreen : PlantyScreen("schedule")

