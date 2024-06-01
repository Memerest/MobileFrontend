package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("access_token") val token: String
)