package com.example.milktracker.ui.nav

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.milktracker.ui.screen.dashboard.DashboardScreen
import com.example.milktracker.ui.screen.calendar.CalendarScreen
import com.example.milktracker.ui.screen.analytics.AnalyticsScreen
import com.example.milktracker.ui.screen.vendors.VendorScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(
            route = Screen.Dashboard.route,
            enterTransition = { fadeIn(tween(250)) + slideInHorizontally { 300 } },
            exitTransition = { fadeOut(tween(250)) }
        ) { DashboardScreen() }
        composable(
            route = Screen.Calendar.route,
            enterTransition = { fadeIn(tween(250)) + slideInVertically { 300 } },
            exitTransition = { fadeOut(tween(250)) }
        ) { CalendarScreen() }
        composable(
            route = Screen.Analytics.route,
            enterTransition = { fadeIn(tween(250)) },
            exitTransition = { fadeOut(tween(250)) }
        ) { AnalyticsScreen() }
        composable(
            route = Screen.Vendors.route,
            enterTransition = { fadeIn(tween(250)) },
            exitTransition = { fadeOut(tween(250)) }
        ) { VendorScreen() }
        composable(
            route = Screen.DailyEntry.route,
            enterTransition = { fadeIn(tween(250)) + slideInVertically { 300 } },
            exitTransition = { fadeOut(tween(250)) }
        ) { /* Entry placeholder */ }
    }
}
