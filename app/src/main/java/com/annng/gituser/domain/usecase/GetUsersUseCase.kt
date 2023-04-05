package com.annng.gituser.domain.usecase

import com.annng.gituser.common.Resource
import com.annng.gituser.domain.model.User
import com.annng.gituser.domain.repository.GitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(val gitRepository: GitRepository) {
    fun loadUserList() : Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val books = gitRepository.getUsers()
            emit(Resource.Success(books))
        } catch (http: HttpException) {
            emit(
                Resource.Error(
                    http.localizedMessage ?: " Something went wrong please try again later!"
                )
            )
        } catch (io: IOException) {
            emit(Resource.Error("Please check your internet connecion and try again later."))
        }

    }.flowOn(Dispatchers.IO)

    fun loadUserDetail(username : String) : Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val books = gitRepository.getUser(username)
            emit(Resource.Success(books))
        } catch (http: HttpException) {
            emit(
                Resource.Error(
                    http.localizedMessage ?: " Something went wrong please try again later!"
                )
            )
        } catch (io: IOException) {
            emit(Resource.Error("Please check your internet connecion and try again later."))
        }

    }.flowOn(Dispatchers.IO)

    fun loadFollowerList(username : String) : Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val books = gitRepository.getUserFollowers(username)
            emit(Resource.Success(books))
        } catch (http: HttpException) {
            emit(
                Resource.Error(
                    http.localizedMessage ?: " Something went wrong please try again later!"
                )
            )
        } catch (io: IOException) {
            emit(Resource.Error("Please check your internet connecion and try again later."))
        }

    }.flowOn(Dispatchers.IO)

}