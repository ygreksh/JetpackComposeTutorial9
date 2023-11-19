package com.codingambitions.jetpackcomposetutorial7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.codingambitions.jetpackcomposetutorial7.screens.DashboardScreen
import com.codingambitions.jetpackcomposetutorial7.screens.MainNavBar
import com.codingambitions.jetpackcomposetutorial7.screens.ProfileDetailScreen
import com.codingambitions.jetpackcomposetutorial7.screens.ProfileScreen
import com.codingambitions.jetpackcomposetutorial7.ui.theme.JetpackComposeTutorial7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorial7Theme {

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
        }
    }
}


