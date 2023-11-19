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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.codingambitions.jetpackcomposetutorial7.TabsNavGraph

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {

        navigation(startDestination = "username", route = "login") {
            composable(route = "username") {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Username",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    TextButton(onClick = {
                        navController.navigate("password")
                    }) {
                        Text(text = "Next")
                    }
                }
            }
            composable(route = "password") {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    TextButton(onClick = {
                        navController.navigate("tabs") {
                            // clear back stack for nested navigation
                            popUpTo("login")
                        }
                    }) {
                        Text(text = "Submit and Go To Home")
                    }
                }
            }
        }


        composable(route = "tabs") {
            TabsNavGraph()
        }

        // Optional arguments
        composable(
            route = "account?userId={userId}",
            arguments = listOf(navArgument("userId") { defaultValue = "1" })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Account Screen",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = "userId=$userId")
                TextButton(onClick = {
                    navController.popBackStack()
                }) {
                    Text(text = "Go Back")
                }
            }
        }
    }
}


sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Username : Screens("username")
    object Password : Screens("password")
    object Home : Screens("home") {
        object ProfileDetail : Screens("profile_detail")
    }
    object Profile : Screens("profile")
}