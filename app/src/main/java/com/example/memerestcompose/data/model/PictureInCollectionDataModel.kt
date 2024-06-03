package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class PictureInCollectionDataModel(
    @SerializedName("id") val pictureId: Int,
    @SerializedName("path") val pictureUrl: String,
)
