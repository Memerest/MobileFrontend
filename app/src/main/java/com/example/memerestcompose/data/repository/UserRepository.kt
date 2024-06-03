package com.example.memerestcompose.data.repository

import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.network.PictureService
import com.example.memerestcompose.data.PreferenceStorage
import com.example.memerestcompose.data.model.RequestLoginModel
import com.example.memerestcompose.data.network.UserService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
    private val service: UserService
) {
    suspend fun login(email: String, password: String): DataResult<Unit> {
        return try {
            val r = service.login(email, password)
            preferenceStorage.userId = r.id
            preferenceStorage.userToken = r.token
            DataResult.Success(Unit)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResult.NetworkError()
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.message()
                    DataResult.GenericError(code, errorResponse)
                }
                else -> {
                    DataResult.GenericError(500, "Strange error")
                }
            }
        }
    }

    suspend fun register(email: String, password: String): DataResult<Unit> {
        return try {
            val r = service.register(RequestLoginModel(email, password))
            preferenceStorage.userId = r.id
            preferenceStorage.userToken = r.token
            DataResult.Success(Unit)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResult.NetworkError()
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.message()
                    DataResult.GenericError(code, errorResponse)
                }

                else -> {
                    DataResult.GenericError(500, "Internal server error")
                }
            }
        }
    }
}