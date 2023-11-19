package com.codingambitions.jetpackcomposetutorial7.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codingambitions.jetpackcomposetutorial7.MainScreens
import com.codingambitions.jetpackcomposetutorial7.items

@Composable
fun MainNavBar(
    navController: NavHostController
) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination
        items.forEach { screen ->
            AddItem(
                mainScreens = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    mainScreens: MainScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == mainScreens.route
        } == true,
        label = { Text(text = mainScreens.title) },
        icon = {
            Icon(
                imageVector = if (mainScreens.route == "home") Icons.Default.Home else Icons.Default.AccountBox,
                contentDescription = null
            )
        },
        onClick = {
            navController.navigate(mainScreens.route) {


                // findStartDestination -> Finds the actual start destination of the graph,
                // handling cases where the graph's starting destination is itself a NavGraph(nested navigation)

                // popUpTo :-  clears the back stack and the state of all
                // destinations between the current destination and the NavOptionsBuilder.popUpTo ID
                // But if we use saveState = true, it will save that state( back stack and the state of all
                // destinations between the current destination and the NavOptionsBuilder.popUpTo ID)
                // before it clears backstack entries upto popUpTo ID,
                // and later it restore that backstack if we use restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true

                restoreState = true

            }
        },
    )
}