package com.example.memerestcompose.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.repository.UserRepository
import com.example.memerestcompose.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {

    private val _uiState = MutableLiveData<UiState<Boolean>>(UiState.Idle())
    val uiState: LiveData<UiState<Boolean>> = _uiState

    fun login(email: String, password: String) {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            when(val loginData = repository.login(email, password)) {
                is DataResult.GenericError -> _uiState.value = UiState.Failure(loginData.error)
                is DataResult.Success -> _uiState.value = UiState.Success(true)
                is DataResult.NetworkError -> _uiState.value = UiState.Failure("Network error!")
            }
        }
    }

    fun refresh() {
        _uiState.value = UiState.Idle()
    }

    fun register(email: String, password: String) {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            when(val loginData = repository.register(email, password)) {
                is DataResult.GenericError -> _uiState.value = UiState.Failure(loginData.error)
                is DataResult.Success -> _uiState.value = UiState.Success(true)
                is DataResult.NetworkError -> _uiState.value = UiState.Failure("Network error!")
            }
        }
    }
}