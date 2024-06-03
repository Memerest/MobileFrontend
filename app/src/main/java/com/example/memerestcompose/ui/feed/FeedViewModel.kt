package com.example.memerestcompose.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.memerestcompose.data.repository.FeedRepository
import com.example.memerestcompose.ui.models.MemeListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {
    val memeList = repository.provideFeed().map { data ->
        data.map { MemeListUiModel(it.pictureId, it.pictureUrl, it.liked) }
    }.cachedIn(viewModelScope)

    fun pressLike(item: MemeListUiModel) {
        viewModelScope.launch {
            repository.sendLike(item.id)
        }
    }
}