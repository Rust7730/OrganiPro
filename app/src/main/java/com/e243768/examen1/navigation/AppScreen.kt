package com.e243768.examen1.navigation

sealed class AppScreens(val route: String) {
    data object Dashboard : AppScreens("dashboard")
    data object Theme : AppScreens("theme")
    data object Form : AppScreens("form")
}