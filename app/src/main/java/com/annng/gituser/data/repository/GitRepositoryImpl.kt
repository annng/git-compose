package com.annng.gituser.data.repository

import com.annng.gituser.data.remote.GitApi
import com.annng.gituser.domain.model.User
import com.annng.gituser.domain.repository.GitRepository
import retrofit2.Response
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(private val api : GitApi) : GitRepository{
    override suspend fun getUsers(): List<User> {
        return api.getUsers()
    }

    override suspend fun getUser(username : String): User {
        return api.getUsers(username)
    }

    override suspend fun getUserFollowers(username : String): List<User> {
        return api.getUsersFollowers(username)
    }
}