package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class AddPictureToCollectionModel(
    @SerializedName("id") val idCollection: Int,
    @SerializedName("pictures") val idPic: List<Int>
)