package com.e243768.examen1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.e243768.examen1.ui.dashboard.DashboardScreen
import com.e243768.examen1.ui.form.FormScreen
import com.e243768.examen1.ui.form.FormViewModel
import com.e243768.examen1.ui.settings.SettingsViewModel
import com.e243768.examen1.ui.settings.ThemeScreen

@Composable
fun AppNavigation(settingsViewModel: SettingsViewModel,
                  formViewModel: FormViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Dashboard.route
    ) {
        composable(AppScreens.Dashboard.route) {
            DashboardScreen(
                onNavigateToTheme = { navController.navigate(AppScreens.Theme.route) },
                onNavigateToForm = { navController.navigate(AppScreens.Form.route) }
            )
        }

        composable(AppScreens.Theme.route) {
            ThemeScreen(
                viewModel = settingsViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreens.Form.route) {
            FormScreen(
                viewModel = formViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}