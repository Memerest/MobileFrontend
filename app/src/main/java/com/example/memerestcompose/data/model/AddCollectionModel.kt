package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class AddCollectionModel(@SerializedName("author_id") val idUser: Int,
                              @SerializedName("name") val name: String,
                              @SerializedName("pictures") val idPic: List<Int> = listOf()
)
