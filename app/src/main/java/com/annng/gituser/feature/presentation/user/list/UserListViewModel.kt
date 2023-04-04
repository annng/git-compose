package com.annng.gituser.feature.presentation.user.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annng.gituser.common.Resource
import com.annng.gituser.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getUserUseCase : GetUsersUseCase
) : ViewModel() {

    private var getUserJob: Job? = null
    private val _state = mutableStateOf(UserListState())
    val state: State<UserListState> = _state

    init {
        getBooks()
    }


    fun getBooks() {
        getUserJob?.cancel()
        getUserJob = getUserUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = UserListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = UserListState(users = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserListState(error = result.message ?: "Something went wrong!")
                }
            }
        }.launchIn(viewModelScope)
    }
}