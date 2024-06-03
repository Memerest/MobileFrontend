package com.example.memerestcompose.data.model

import com.google.gson.annotations.SerializedName

data class CollectionsResponse(
    @SerializedName("author_id") val authorId: Int,
    @SerializedName("id") val collectionId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("pictures") val pictures: List<PictureInCollectionDataModel>)