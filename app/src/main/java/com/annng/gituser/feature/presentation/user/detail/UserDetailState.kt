package com.annng.gituser.feature.presentation.user.detail

import com.annng.gituser.domain.model.User

data class UserDetailState(
        val isLoading: Boolean = false,
        val users: User? = null,
        val error: String = ""
    )