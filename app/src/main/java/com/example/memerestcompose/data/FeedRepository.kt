package com.example.memerestcompose.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.model.PictureDataModel
import com.example.memerestcompose.ui.models.CollectionUIModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val picService: NetworkService,
    preferenceStorage: PreferenceStorage
) {
    private val userId = preferenceStorage.userId
    fun provideFeed(collectionId: Int? = null) = Pager(config = PagingConfig(
        pageSize = LOAD_SIZE, enablePlaceholders = true
    ), pagingSourceFactory = {
        FeedPagingSource(picService, collectionId, userId)
    }).flow


    suspend fun sendLike(pictureId: Int) {
        picService.likePicture(pictureId, userId)
    }

    suspend fun fetchCollections(): DataResult<List<CollectionUIModel>> {
        return try {
            DataResult.Success(picService.getCollectionList())
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