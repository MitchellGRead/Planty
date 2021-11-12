package com.example.planty.ui.navigation

sealed class PlantyRoute(val route: String)

object HomeScreenRoute : PlantyRoute("home")
object ScheduleScreenRoute : PlantyRoute("schedule")
object CameraRoute : PlantyRoute("camera")

