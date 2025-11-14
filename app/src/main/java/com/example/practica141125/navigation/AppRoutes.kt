package com.example.practica141125.navigation

object RouteArgs {
    const val STUDENT_ID = "studentId"
}

object Routes {
    const val DASHBOARD_ROUTE = "dashboard_route"
    const val ANALYTICS_ROUTE = "analytics_route"

    const val DASHBOARD_MAIN = "dashboard_main"
    const val ADD_STUDENT = "add_student"
    const val EDIT_STUDENT = "edit_student/{${RouteArgs.STUDENT_ID}}"
}

sealed class TabScreen(val route: String, val title: String) {
    object Dashboard : TabScreen(Routes.DASHBOARD_ROUTE, "Dashboard")
    object Analytics : TabScreen(Routes.ANALYTICS_ROUTE, "Análisis")
}