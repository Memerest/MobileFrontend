package com.example.memerestcompose.ui

sealed class UiState<T>() {
    class Idle<T> : UiState<T>()
    class Loading<T> : UiState<T>()
    class Success<T>(val data: T) : UiState<T>()
    class Failure<T>(val e: String) : UiState<T>()
}