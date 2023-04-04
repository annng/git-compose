package com.annng.gituser.domain.repository

import com.annng.gituser.domain.model.User

interface GitRepository {
    suspend fun getUsers(): List<User>
}