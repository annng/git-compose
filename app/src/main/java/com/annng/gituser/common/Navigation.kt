package com.annng.gituser.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.annng.gituser.feature.presentation.user.list.UserListScreen

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    )
    {
        composable(route = Screen.UserList.route) {
            UserListScreen(navController = navController)
        }
    }
}