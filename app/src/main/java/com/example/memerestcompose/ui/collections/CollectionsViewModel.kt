package com.example.memerestcompose.ui.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.FeedRepository
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.models.CollectionUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(private val feedRepository: FeedRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<CollectionUIModel>>>(UiState.Idle())
    val uiState: LiveData<UiState<List<CollectionUIModel>>> = _uiState

    fun fetchCollections() {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            when(val loginData = feedRepository.fetchCollections()) {
                is DataResult.GenericError -> _uiState.value = UiState.Failure(loginData.error)
                is DataResult.Success -> _uiState.value = UiState.Success(loginData.data)
                is DataResult.NetworkError -> _uiState.value = UiState.Failure("Network error!")
            }
        }
    }

    fun fetchCollectionFeed(collectionId: Int?) = viewModelScope.launch {  feedRepository.provideFeed(collectionId) }
}