package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class PictureDataModel(
    @SerializedName("id") val pictureId: Int,
    @SerializedName("path") val pictureUrl: String,
    @SerializedName("is_liked_by_user") var liked: Boolean,
)
