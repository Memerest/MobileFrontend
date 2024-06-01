package com.example.memerestcompose.data

import com.example.memerestcompose.data.model.LikeDataModel
import com.example.memerestcompose.data.model.PictureDataModel
import com.example.memerestcompose.data.model.RequestLoginModel
import com.example.memerestcompose.data.model.UserDataModel
import com.example.memerestcompose.ui.models.CollectionUIModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface NetworkService {

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

    @FormUrlEncoded
    @POST("auth/token")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserDataModel

    @POST("auth/register/")
    suspend fun register(@Body requestLoginModel: RequestLoginModel): UserDataModel

    suspend fun getCollectionList(): List<CollectionUIModel>

    suspend fun getCollectionPictures(
        id: Int, userId: Int
    ): List<PictureDataModel>
}