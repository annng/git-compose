package com.annng.gituser.feature.presentation.user.list

import com.annng.gituser.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User>? = null,
    val error: String = ""
)
