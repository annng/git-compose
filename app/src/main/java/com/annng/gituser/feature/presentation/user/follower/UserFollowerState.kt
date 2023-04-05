package com.annng.gituser.feature.presentation.user.follower

import com.annng.gituser.domain.model.User

data class UserFollowerState(
    val isLoading: Boolean = false,
    val users: List<User>? = null,
    val error: String = ""
)