package com.example.footballleaguetask.presentation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object CompetitionDetails : Screen("competition_details/{competitionId}") {
        fun createRoute(competitionId: Int) = "competition_details/$competitionId"
    }
}
