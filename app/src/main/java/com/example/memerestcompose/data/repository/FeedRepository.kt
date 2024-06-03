package com.example.memerestcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.memerestcompose.data.FeedPagingSource
import com.example.memerestcompose.data.LOAD_SIZE
import com.example.memerestcompose.data.network.PictureService
import com.example.memerestcompose.data.PreferenceStorage
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.model.LikeDataModel
import com.example.memerestcompose.data.model.PictureDataModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val picService: PictureService, preferenceStorage: PreferenceStorage
) {
    private val userId = preferenceStorage.userId
    fun provideFeed() = Pager(config = PagingConfig(
        pageSize = LOAD_SIZE, enablePlaceholders = true
    ), pagingSourceFactory = {
        FeedPagingSource(picService, userId)
    }).flow


    suspend fun sendLike(pictureId: Int) {
        picService.likePicture(LikeDataModel(pictureId, userId))
    }

    suspend fun fetchWeekly(): DataResult<List<PictureDataModel>> {
        return try {
            DataResult.Success(picService.getWeeklyList(userId))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResult.NetworkError()
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.message()
                    DataResult.GenericError(code, errorResponse)
                }

                else -> {
                    DataResult.GenericError(500, "Strange error")
                }
            }
        }
    }

}