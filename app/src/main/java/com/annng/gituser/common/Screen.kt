package com.annng.gituser.common

sealed class Screen(val route: String) {
    object UserList : Screen("user_list")
    object UserDetail : Screen("user_detail")
    object UserFollowe : Screen("user_follower")
}
