package com.e243768.ex_u3.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen/{pokemonName}") {
        fun createRoute(pokemonName: String) = "detail_screen/$pokemonName"
    }
    object Favorites : Screen("favorites_screen")
}