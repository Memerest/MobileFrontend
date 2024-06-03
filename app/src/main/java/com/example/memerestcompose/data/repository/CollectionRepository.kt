package com.example.memerestcompose.data.repository

import com.example.memerestcompose.data.PreferenceStorage
import com.example.memerestcompose.data.model.AddCollectionModel
import com.example.memerestcompose.data.model.AddPictureToCollectionModel
import com.example.memerestcompose.data.model.CollectionsResponse
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.model.PictureInCollectionDataModel
import com.example.memerestcompose.data.network.PictureService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CollectionRepository @Inject constructor(
    private val picService: PictureService, preferenceStorage: PreferenceStorage
) {
    private val userId = preferenceStorage.userId

    suspend fun fetchCollections(): DataResult<List<CollectionsResponse>> {
        return try {
            DataResult.Success(picService.getCollectionList(userId.toString()))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResult.NetworkError()
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.message()
                    DataResult.GenericError(code, errorResponse)
                }

                else -> {
                    DataResult.GenericError(500, throwable.message!!)
                }
            }
        }
    }

    suspend fun fetchCollectionFeed(collectionId: Int): DataResult<List<PictureInCollectionDataModel>> {
        return try {
            DataResult.Success(
                picService.getCollectionList(userId.toString())
                    .find { it.collectionId == collectionId }!!.pictures
            )
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

    suspend fun addPicToCollection(collectionId: Int, picId: Int): DataResult<Unit> {
        return try {
            picService.addPicToCollection(AddPictureToCollectionModel(collectionId, listOf(picId)))
            DataResult.Success(Unit)
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

    suspend fun addCollection(collectionName: String) : DataResult<Unit> {
        return try {
            picService.addCollection(AddCollectionModel(userId, collectionName))
            DataResult.Success(Unit)
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