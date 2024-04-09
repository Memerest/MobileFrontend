package com.example.memerest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Integer.max

private const val STARTING_KEY = 0

class MemePagingSource : PagingSource<Int, Meme>() {
    override fun getRefreshKey(state: PagingState<Int, Meme>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val meme = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = meme.id - (state.config.pageSize / 2))

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meme> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)
        return LoadResult.Page(
            data = range.map { number ->
                Meme(
                    id = number,
                    url = "https://assets.rebelmouse.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbWFnZSI6Imh0dHBzOi8vYXNzZXRzLnJibC5tcy8xNDY0MDI4Ny9vcmlnaW4uanBnIiwiZXhwaXJlc19hdCI6MTY2NTI4NDA0MH0.N9u1yIQCtEslfi1k2tJ5RweJRZYuMTIymMdVDfI9Woc/img.jpg"
                )
            },
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}