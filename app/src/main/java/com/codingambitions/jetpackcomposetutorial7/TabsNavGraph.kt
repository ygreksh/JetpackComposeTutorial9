package com.codingambitions.jetpackcomposetutorial7

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
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
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.Tabs.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            tabsNavGraph(navController = navController)
        }

    }
}

val items = listOf(
    MainScreens.Home,
    MainScreens.Profile,
)

sealed class MainScreens(val route: String, val title: String) {
    object Home : MainScreens(Screens.Tabs.Home.route, "Home")
    object Profile : MainScreens(Screens.Tabs.Profile.route, "Profile")
}

fun NavGraphBuilder.tabsNavGraph(navController: NavHostController) {
    navigation(
        route = Screens.Tabs.route,
        startDestination = Screens.Tabs.Home.route,
    ) {
        homeNavGraph(navController = navController)
        composable(route = Screens.Tabs.Profile.route) {
            ProfileScreen()
        }
    }
}

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Screens.Tabs.Home.route,
        startDestination = Screens.Tabs.Home.Dashboard.route,
    ) {
        composable(route = Screens.Tabs.Home.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(route = Screens.Tabs.Home.ProfileDetail.route) {
            ProfileDetailScreen(navController = navController)
        }
    }
}