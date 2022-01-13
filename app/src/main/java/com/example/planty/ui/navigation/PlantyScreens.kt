package com.example.planty.ui.navigation

sealed class PlantyScreen(val route: String)

object HomeScreen : PlantyScreen("home")
object CreateEntryScreen : PlantyScreen("create/entry")
object EntryInfoScreen : PlantyScreen("entry/info")
object ScheduleScreen : PlantyScreen("schedule")

