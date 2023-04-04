package com.annng.gituser.data.remote

import com.annng.gituser.domain.model.User
import retrofit2.Response
import retrofit2.http.GET

interface GitApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}