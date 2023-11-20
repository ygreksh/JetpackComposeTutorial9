package com.codingambitions.jetpackcomposetutorial7.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.codingambitions.jetpackcomposetutorial7.TabsNavGraph

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {

        loginNavGraph(navController = navController)

        composable(route = Screens.Tabs.route) {
            TabsNavGraph()
        }


        // Optional arguments
//        composable(
//            route = "account?userId={userId}",
//            arguments = listOf(navArgument("userId") { defaultValue = "1" })
//        ) { backStackEntry ->
//            val userId = backStackEntry.arguments?.getString("userId")
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Account Screen",
//                    style = MaterialTheme.typography.headlineMedium
//                )
//                Text(text = "userId=$userId")
//                TextButton(onClick = {
//                    navController.popBackStack()
//                }) {
//                    Text(text = "Go Back")
//                }
//            }
//        }
    }
}


sealed class Screens(val route: String) {
    object Login : Screens("login") {
        object Username : Screens("username")
        object Password : Screens("password")
    }

    object Tabs : Screens("tabs") {
        object Home : Screens("home") {
            object Dashboard : Screens("dashboard")
            object ProfileDetail : Screens("profile_detail")
        }
        object Profile : Screens("profile")
    }
}

fun NavGraphBuilder.loginNavGraph(navController: NavHostController) {
    navigation(startDestination = Screens.Login.Username.route, route = Screens.Login.route) {
        composable(route = Screens.Login.Username.route) {
            UsernameScreen(navController = navController)
        }
        composable(route = Screens.Login.Password.route) {
            PasswordScreen(navController = navController)
        }
    }
}