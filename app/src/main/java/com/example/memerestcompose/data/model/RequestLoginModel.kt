package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class RequestLoginModel(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)