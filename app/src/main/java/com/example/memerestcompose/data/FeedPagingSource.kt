package com.example.memerestcompose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.memerestcompose.data.model.PictureDataModel
import com.example.memerestcompose.data.network.PictureService
import javax.inject.Inject

const val LOAD_SIZE = 10

class FeedPagingSource @Inject constructor(
    private val picService: PictureService,
    private val userId: Int
) : PagingSource<Int, PictureDataModel>() {
    override fun getRefreshKey(state: PagingState<Int, PictureDataModel>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PictureDataModel> {
        val currentPage = params.key ?: 0
        return try {
            val list =
                picService.getList(userId, LOAD_SIZE)
            LoadResult.Page(
                data = list,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (currentPage > 5) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}