package com.example.memerestcompose.data.model

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class GenericError<T>(val code: Int, val error: String) : DataResult<T>()
    class NetworkError<T> : DataResult<T>()
}