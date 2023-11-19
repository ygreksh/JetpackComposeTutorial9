package com.codingambitions.jetpackcomposetutorial7

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.codingambitions.jetpackcomposetutorial7.screens.DashboardScreen
import com.codingambitions.jetpackcomposetutorial7.screens.MainNavBar
import com.codingambitions.jetpackcomposetutorial7.screens.ProfileDetailScreen
import com.codingambitions.jetpackcomposetutorial7.screens.ProfileScreen
import com.codingambitions.jetpackcomposetutorial7.screens.Screens

@Composable
fun TabsNavGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainNavBar(navController = navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.Tabs.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(startDestination = Screens.Tabs.Home.Dashboard.route, route = Screens.Tabs.Home.route) {
                composable(route = Screens.Tabs.Home.Dashboard.route) {
                    DashboardScreen(navController = navController)
                }
                composable(route = Screens.Tabs.Home.ProfileDetail.route) {
                    ProfileDetailScreen(navController = navController)
                }
            }
            composable(route = Screens.Tabs.Profile.route) {
                ProfileScreen()
            }
        }

    }
}

val items = listOf(
    MainScreens.Home,
    MainScreens.Profile,
)

sealed class MainScreens(val route: String, val title: String) {
    object Home : MainScreens("home", "Home")
    object Profile : MainScreens("profile", "Profile")
}
