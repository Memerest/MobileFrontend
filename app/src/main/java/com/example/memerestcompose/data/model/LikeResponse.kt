package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("is_liked") val isLiked: Boolean,
    @SerializedName("picture_id") val pictureId: Int,
    @SerializedName("user_id") val userId: Int)