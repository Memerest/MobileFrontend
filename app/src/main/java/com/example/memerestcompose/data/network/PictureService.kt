package com.example.memerestcompose.data.network

import com.example.memerestcompose.data.model.AddCollectionModel
import com.example.memerestcompose.data.model.AddPictureToCollectionModel
import com.example.memerestcompose.data.model.CollectionsResponse
import com.example.memerestcompose.data.model.LikeDataModel
import com.example.memerestcompose.data.model.PictureDataModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PictureService {
    @PUT("/backend/users/like/")
    suspend fun likePicture(@Body likeDataModel: LikeDataModel)

    @POST("recommendations/recommendations/")
    suspend fun getList(
        @Query("user_id") userId: Int,
        @Query("num_recommendations") limit: Int
    ): List<PictureDataModel>

    @GET("recommendations/top_pictures/")
    suspend fun getWeeklyList(
        @Query("user_id") userId: Int,
    ): List<PictureDataModel>

    @GET("/backend/collections/id/{userId}")
    suspend fun getCollectionList(@Path(value = "userId", encoded = true) userId: String): List<CollectionsResponse>

    @PUT("/backend/collections/add_pics/")
    suspend fun addPicToCollection(@Body addPictureToCollectionModel: AddPictureToCollectionModel): CollectionsResponse

    @POST("/backend/collections")
    suspend fun addCollection(@Body addCollectionModel: AddCollectionModel): CollectionsResponse
}