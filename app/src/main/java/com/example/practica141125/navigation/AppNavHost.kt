package com.example.practica141125.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.practica141125.viewmodels.ViewModelFactory
import com.example.practica141125.views.AddEditStudentView
import com.example.practica141125.views.AnalyticsView
import com.example.practica141125.views.DashboardView

@Composable
fun AppNavHost(
    navController: NavHostController,
    navManager: NavManager,
    viewModelFactory: ViewModelFactory,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TabScreen.Dashboard.route,
        modifier = modifier
    ) {

        navigation(
            startDestination = Routes.DASHBOARD_MAIN,
            route = TabScreen.Dashboard.route
        ) {
            composable(Routes.DASHBOARD_MAIN) {
                DashboardView(
                    navManager = navManager,
                    viewModelFactory = viewModelFactory
                )
            }

            composable(Routes.ADD_STUDENT) {
                AddEditStudentView(
                    navManager = navManager,
                    viewModelFactory = viewModelFactory
                )
            }

            composable(
                route = Routes.EDIT_STUDENT,
                arguments = listOf(navArgument(RouteArgs.STUDENT_ID) { type = NavType.IntType })
            ) { backStackEntry ->
                val studentId = backStackEntry.arguments?.getInt(RouteArgs.STUDENT_ID)
                AddEditStudentView(
                    navManager = navManager,
                    studentId = studentId,
                    viewModelFactory = viewModelFactory
                )
            }
        }

        composable(TabScreen.Analytics.route) {
            AnalyticsView(
                viewModelFactory = viewModelFactory
            )
        }
    }
}