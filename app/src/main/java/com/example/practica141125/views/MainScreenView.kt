package com.example.practica141125.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.practica141125.data.AppDatabase
import com.example.practica141125.navigation.AppNavHost
import com.example.practica141125.navigation.NavManager
import com.example.practica141125.navigation.TabScreen
import com.example.practica141125.viewmodels.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val navManager = remember { NavManager(navController) }

    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "student-db"
        ).build()
    }
    val studentDao = remember { db.studentDao() }
    val viewModelFactory = remember { ViewModelFactory(studentDao) }

    val tabScreens = listOf(TabScreen.Dashboard, TabScreen.Analytics)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                tabScreens.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navManager.navigateToTab(screen.route)
                        },
                        icon = { }
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            navManager = navManager,
            viewModelFactory = viewModelFactory,
            modifier = Modifier.padding(innerPadding)
        )
    }
}