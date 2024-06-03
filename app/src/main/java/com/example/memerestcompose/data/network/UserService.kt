package com.example.memerestcompose.data.network

import com.example.memerestcompose.data.model.RequestLoginModel
import com.example.memerestcompose.data.model.UserDataModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST("auth/token")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserDataModel

    @POST("auth/register/")
    suspend fun register(@Body requestLoginModel: RequestLoginModel): UserDataModel

}