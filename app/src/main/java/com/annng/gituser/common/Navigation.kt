package com.annng.gituser.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.annng.gituser.feature.presentation.user.detail.UserDetailScreen
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
        //userlist
        composable(route = Screen.UserList.route) {
            UserListScreen(navController = navController)
        }

        //userdetail
        composable(route = Screen.UserDetail.route + "?username={username}",
        arguments = listOf(navArgument("username"){
            type =  NavType.StringType
        })
        ){
            val username = it.arguments?.getString("username")

            if(!username.isNullOrEmpty()){
                UserDetailScreen(navController = navController, username = username)
            }
        }
    }
}