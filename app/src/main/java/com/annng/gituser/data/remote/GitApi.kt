package com.annng.gituser.data.remote

import com.annng.gituser.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{username}")
    suspend fun getUsers(
        @Path("username") username : String
    ): User
}