package com.example.memerestcompose.ui.collections.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.repository.CollectionRepository
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.models.MemeListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CollectionFeedViewModel @Inject constructor(private val feedRepository: CollectionRepository) :
    ViewModel() {
    private val _uiStateFeed = MutableLiveData<UiState<List<MemeListUiModel>>>(UiState.Idle())
    val uiStateFeed: LiveData<UiState<List<MemeListUiModel>>> = _uiStateFeed

    fun fetchCollectionFeed(collectionId: Int) {
        _uiStateFeed.value = UiState.Loading()
        viewModelScope.launch {
            when (val collections = feedRepository.fetchCollectionFeed(collectionId)) {
                is DataResult.GenericError -> _uiStateFeed.value =
                    UiState.Failure(collections.error)

                is DataResult.Success -> _uiStateFeed.value = UiState.Success(collections.data.map {
                    MemeListUiModel(
                        it.pictureId, it.pictureUrl, false
                    )
                })

                is DataResult.NetworkError -> _uiStateFeed.value = UiState.Failure("Network error!")
            }
        }
    }
}