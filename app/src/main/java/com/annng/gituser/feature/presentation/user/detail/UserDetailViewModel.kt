package com.annng.gituser.feature.presentation.user.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.annng.gituser.common.Resource
import com.annng.gituser.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annng.gituser.feature.presentation.user.follower.UserFollowerState

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userUseCase : GetUsersUseCase
) : ViewModel() {
    private var getUserJob: Job? = null
    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    private var getFollowUser: Job? = null
    private val _followeState = mutableStateOf(UserFollowerState())
    val followerState: State<UserFollowerState> = _followeState

    fun getDetailUser(username : String) {
        getUserJob?.cancel()
        getUserJob = userUseCase.loadUserDetail(username).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(
                        error = result.message ?: "Something went wrong!"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getFollowerUser(username : String) {
        getUserJob?.cancel()
        getUserJob = userUseCase.loadFollowerList(username).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _followeState.value = UserFollowerState(isLoading = true)
                }
                is Resource.Success -> {
                    _followeState.value = UserFollowerState(users = result.data)
                }
                is Resource.Error -> {
                    _followeState.value = UserFollowerState(
                        error = result.message ?: "Something went wrong!"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}