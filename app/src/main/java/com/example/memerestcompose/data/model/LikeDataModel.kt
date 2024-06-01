package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class LikeDataModel(
    @SerializedName("picture_id") val pictureId: Int,
    @SerializedName("user_id") val userId: Int)