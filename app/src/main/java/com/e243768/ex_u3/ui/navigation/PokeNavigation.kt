package com.e243768.ex_u3.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.e243768.ex_u3.ui.screens.LoadingScreen
import com.e243768.ex_u3.ui.screens.detail.DetailScreen
import com.e243768.ex_u3.ui.screens.favorites.FavoritesScreen
import com.e243768.ex_u3.ui.screens.home.SearchScreen

@Composable
fun PokeNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            LoadingScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            SearchScreen(navController = navController)
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: return@composable

            DetailScreen(
                navController = navController,
                pokemonName = pokemonName
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
    }
}