package com.example.milktracker.ui.nav

sealed class Screen(val route: String, val title: String) {
    data object Dashboard : Screen("dashboard", "Dashboard")
    data object DailyEntry : Screen("entry", "Log Entry")
    data object Calendar : Screen("calendar", "Calendar")
    data object Analytics : Screen("analytics", "Analytics")
    data object Vendors : Screen("vendors", "Vendors")
    data object Export : Screen("export", "Export")
}
