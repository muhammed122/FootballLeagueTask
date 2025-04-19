package com.example.footballleaguetask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.footballleaguetask.presentation.details.CompetitionDetailsScreen
import com.example.footballleaguetask.presentation.home.HomeScreen

@Composable
fun FootballNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToDetails = { id ->
                    navController.navigate(Screen.CompetitionDetails.createRoute(id))
                }
            )
        }
        composable(
            route = Screen.CompetitionDetails.route,
            arguments = listOf(navArgument("competitionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("competitionId") ?: return@composable
            CompetitionDetailsScreen(id = id)
        }
    }
}
