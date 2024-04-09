package com.example.memerest.presentation.main.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.memerest.data.Meme
import com.example.memerest.data.MemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 50

class FeedViewModel @Inject constructor(private val repository: MemeRepository): ViewModel() {
    val items: Flow<PagingData<Meme>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { repository.memePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}