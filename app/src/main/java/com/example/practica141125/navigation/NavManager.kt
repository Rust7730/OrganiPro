package com.example.practica141125.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class NavManager(val navController: NavController) {

    fun navigateTo(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToTab(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToEditStudent(studentId: Int) {
        val route = Routes.EDIT_STUDENT.replace("{${RouteArgs.STUDENT_ID}}", "$studentId")
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.popBackStack()
    }
}